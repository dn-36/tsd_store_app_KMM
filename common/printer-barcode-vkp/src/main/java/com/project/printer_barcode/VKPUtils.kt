package com.project.printer_barcode

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.DisplayMetrics
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

object VKPUtils {

    fun textToBitmap(
        text: String,
        _width: Int,
        fontSize: Float,
        isBold: Boolean
    ): Bitmap {
        // Initialize paint object
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val width = (_width * 1.5F).toInt()
        paint.textSize = fontSize * 1.5F
        paint.isFakeBoldText = isBold
        paint.color = android.graphics.Color.BLACK
        paint.textAlign = Paint.Align.CENTER // Центровка текста

        // Увеличение межбуквенного интервала
        paint.letterSpacing = 0.08f

        // Calculate the height needed
        val textRect = Rect()
        paint.getTextBounds(text, 0, text.length, textRect)
        val lineHeight = textRect.height()
        val lines = mutableListOf<String>()

        var yOffset = lineHeight
        var currentLine = StringBuilder()
        var currentWidth = 0

        for (word in text.split(" ")) {
            var remainingWord = word

            while (remainingWord.isNotEmpty()) {
                val wordWidth = paint.measureText(remainingWord)

                // Если текущее слово не помещается
                if (currentWidth + wordWidth > width) {
                    if (currentWidth == 0) {
                        // Если слово слишком длинное, разбиваем его
                        var fitChars = 0
                        while (fitChars < remainingWord.length) {
                            val subText = remainingWord.substring(0, fitChars + 1)
                            val subWidth = paint.measureText(subText)
                            if (subWidth > width) break
                            fitChars++
                        }
                        // Добавляем часть слова на текущую строку
                        currentLine.append(remainingWord.substring(0, fitChars))
                        remainingWord = remainingWord.substring(fitChars)
                    } else {
                        // Если слово не влезает, переносим его на новую строку
                        lines.add(currentLine.toString().trim())
                        currentLine = StringBuilder()
                        currentWidth = 0
                        yOffset += lineHeight
                        continue
                    }
                } else {
                    // Слово помещается полностью
                    currentLine.append("$remainingWord ")
                    currentWidth += wordWidth.toInt() + paint.measureText(" ").toInt()
                    remainingWord = ""
                }
            }
        }

        // Добавляем последнюю строку, если она не пуста
        if (currentLine.isNotEmpty()) {
            lines.add(currentLine.toString().trim())
            yOffset += lineHeight
        }

        val totalHeight = yOffset

        // Increase pixel density by a factor of 5
        val densityMultiplier = 5
        val scaledWidth = width * densityMultiplier
        val scaledHeight = totalHeight * densityMultiplier

        // Create bitmap and canvas to draw on
        val bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
        bitmap.setDensity(DisplayMetrics.DENSITY_HIGH) // Optional, for high-density screens
        val canvas = Canvas(bitmap)
        canvas.scale(densityMultiplier.toFloat(), densityMultiplier.toFloat()) // Scale the canvas

        // Set the canvas background color
        canvas.drawColor(android.graphics.Color.WHITE)

        // Draw the text onto the canvas, centered
        var yPos = lineHeight.toFloat()
        for (line in lines) {
            // Calculate the x-coordinate to center the line
            canvas.drawText(line, width / 2f, yPos, paint)
            yPos += lineHeight
        }

        return bitmap
    }

    fun setSizeBitMap(
        originalBitmap: Bitmap,
        widthDp: Int,
        heightDp: Int,
        context: Context
    ): Bitmap {
        val heightPx = (heightDp * context.resources.displayMetrics.density).toInt()
        val widthPx = (widthDp * context.resources.displayMetrics.density).toInt()
        return Bitmap.createScaledBitmap(originalBitmap, widthPx, heightPx, false)
    }

    fun generateBarcode(content: String, heightMm: Int): Bitmap? {
        return try {
            val pixelsPerMm = 3.779528
            val widthPx = (75 * pixelsPerMm).toInt()
            val heightPx = (heightMm * pixelsPerMm).toInt()

            // Create a Bitmap to draw on
            val bitmap = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            val barcodeEncoder = BarcodeEncoder()
            val barcodeBitmap =
                barcodeEncoder.encodeBitmap(content, BarcodeFormat.CODE_128, widthPx, heightPx)

            val left = (widthPx - barcodeBitmap.width) / 2
            val top = (heightPx - barcodeBitmap.height) / 2

            canvas.drawBitmap(barcodeBitmap, left.toFloat(), top.toFloat(), null)

            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}