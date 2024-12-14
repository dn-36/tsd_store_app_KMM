package com.profile.profile.udpPlayer.rtsp_protocol

import android.os.StrictMode
import android.util.Log
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import java.io.File
import java.io.IOException
import java.lang.Thread.sleep

data class FileDataModel(val name: String, val size: Long, val isDir: Boolean)

class FileData(private val sftpUrl: String?) {

    companion object {
        private var session: Session? = null
        private var channel: ChannelSftp? = null

        fun getParentPath(remotePath: String): String {
            val p = if (remotePath.endsWith("/"))
                remotePath.substring(0, remotePath.length - 1)
            else
                remotePath
            return p.substring(0, p.lastIndexOf("/") + 1)
        }

        fun getTmpFile(file: String): File {
            val ext = file.substring(file.lastIndexOf(".") + 1)
            return File(RtspVideoStreamPlayerComponent.context.cacheDir.path + "/video." + ext)
        }
    }

    fun getAll(remotePath: String): MutableList<FileDataModel> {
        val files = mutableListOf<FileDataModel>()

        connect()
        try {
            val ls = channel?.ls(remotePath)
            ls?.forEach {
                val e = it as ChannelSftp.LsEntry
                if (e.filename == "." || e.filename == "..")
                    return@forEach
                val attrs = e.attrs
                val f = FileDataModel(e.filename, attrs.size, attrs.isDir)
                files.add(f)
            }
            files.sortBy { it.name }
        } catch (e: Exception) {
            Log.e("SFTP", "Can't list $remotePath (${e.localizedMessage})")
        } finally {
            disconnect()
        }
        return files
    }

    /**
     * Copy remote video file to local cache.
     *
     * @param path relative SFTP folder's path; starts and ends with "/"
     * @param file file name
     */
    fun remoteToCache(path: String, file: String) {
        val tmpFile = getTmpFile(file)

        try {
            tmpFile.writeText("") // create if not exists and set zero size
        } catch (e: Exception) {
            Log.e("SFTP", "Can't create tmp file (${e.localizedMessage})")
        }
        Thread {
            connect()
            try {
                channel?.get(path + file, tmpFile.absolutePath)
            } catch (e: Exception) {
                Log.e("SFTP", "Can't copy file $path$file (${e.localizedMessage})")
            } finally {
                disconnect()
            }
        }.start()

        var size: Long = 0
        var i = 0
        try {
            while (size == 0L && i++ < 1000) { // wait until downloading will start
                sleep(10)
                size = tmpFile.length()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        sleep(100)
    }

    fun getNext(remotePath: String, fwd: Boolean = true): String {
        try {
            val path = remotePath.split("/")

            val currentName = path.last()
            val parentPath = getParentPath(remotePath)

            // Find a file in current folder
            var files = getAll(parentPath)
            val f = files.find { it.name == currentName }
            val fid = files.indexOf(f)
            if ((fid < files.count() - 1 && fwd) || (fid > 0 && !fwd)) {
                val idx = if (fwd) fid + 1 else fid - 1
                val newName = files[idx].name

                remoteToCache(parentPath, newName)

                return parentPath + newName
            }

            if (path.count() < 3)
                return ""

            // Find next/prev directory
            val grandPath = getParentPath(parentPath)
            val currentDir = path[path.count() - 2]
            val dirs = getAll(grandPath)
            val d = dirs.find { it.name == currentDir }
            val did = dirs.indexOf(d)
            if ((did > dirs.count() - 1 && fwd) || (did == 0 && !fwd)) {
                return ""
            }
            val idx = if (fwd) did + 1 else did - 1
            val newDir = grandPath + dirs[idx].name + "/"

            // Find a file in next directory
            files = getAll(newDir)
            if (files.isEmpty())
                return ""
            val newName = if (fwd) files.first().name else files.last().name

            remoteToCache(newDir, newName)

            return newDir + newName

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    private fun connect() { // suspend
        val sftpData = Utils.parseUrl(sftpUrl, 22, "sftp") ?: return

        val policy = StrictMode.ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)

        try {
            if (channel != null || session != null)
                disconnect()

            val password = Utils.decodeString(sftpData.password)

            val jsch = JSch()
            session = jsch.getSession(sftpData.user, sftpData.host, sftpData.port)
            session?.setPassword(password)
            session?.setConfig("StrictHostKeyChecking", "no")
            session?.connect(3000)

            channel = session?.openChannel("sftp") as ChannelSftp
            channel?.connect(10000)
        } catch (e: Exception) {
            Log.e("SFTP", "Can't connect to ${sftpData.host} (${e.localizedMessage})")
        }
    }

    private fun disconnect() {
        channel?.disconnect()
        session?.disconnect()
        channel = null
        session = null
    }
}