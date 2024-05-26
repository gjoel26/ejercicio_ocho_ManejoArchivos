package com.example.ejercicio_ocho

import android.content.Context
import android.util.Log
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class FileHandling(val context: Context) {
    val nameFile = "data.json"

    fun save(content: String): Boolean {
        var result = false

        try {
            val file = File(context.getExternalFilesDir(null), nameFile)
            val fos = FileOutputStream(file, false)
            val write = OutputStreamWriter(fos)
            write.write(content)
            write.close()
            result = true

        } catch (e: Exception) {
            e.message
            result = false
        }
        return result
    }

    fun readFile(): String {
        val content = StringBuilder()

        try {
            val file = File(context.getExternalFilesDir(null), nameFile)
            if (file.exists()) {
                val fis = FileInputStream(file)
                val read = InputStreamReader(fis)
                var buffer = CharArray(4096)
                var quality = read.read(buffer)
                while (quality != -1) {
                    content.append(buffer, 0, quality)
                    quality = read.read(buffer)
                }
                read.close()
            } else {
                Log.d("FileHandling", "El archivo no existe")
            }

        } catch (e: Exception) {
            Log.e("FileHandling", "Error al leer el archivo", e)
        }
        Log.d("FileHandling", "Contenido del archivo: $content")
        return content.toString()
    }

}