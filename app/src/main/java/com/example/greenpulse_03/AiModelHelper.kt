package com.example.greenpulse_04

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class AiModelHelper(context: Context) {
    private val interpreter: Interpreter

    init {
        val model = loadModelFile(context)
        interpreter = Interpreter(model)
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("crop_schedule.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }

    fun predictBestPlantingDate(temp: Float, rainfall: Float, soilType: Float): Float {
        val input = arrayOf(floatArrayOf(temp, rainfall, soilType))
        val output = arrayOf(floatArrayOf(0f))
        interpreter.run(input, output)
        return output[0][0]
    }
}
