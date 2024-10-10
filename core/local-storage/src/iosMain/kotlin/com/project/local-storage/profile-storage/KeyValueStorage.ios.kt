package com.project.`local-storage`.`profile-storage`

import platform.Foundation.NSUserDefaults


 class StorageIos(
     val context:NSUserDefaults = NSUserDefaults.standardUserDefaults
 ) : KeyValueStorageApi {

   // private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun saveCurrentNumber(key: String, value: String) {
        (context as NSUserDefaults).setObject(value, forKey = key)
    }

    override suspend fun saveCurrentName(key: String, value: String) {
        (context as NSUserDefaults).setObject(value, forKey = key)
    }

    override suspend fun getCurrentNumber(key: String): String? {
        return  (context as NSUserDefaults).stringForKey(key)
    }

    override suspend fun getCurrentName(key: String): String? {
        return  (context as NSUserDefaults).stringForKey(key)
    }
}
