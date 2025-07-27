package com.example.greenpulse_04

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class AiPredictionActivity : AppCompatActivity() {
    private lateinit var viewModel: AiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_prediction)

        viewModel = ViewModelProvider(this).get(AiViewModel::class.java)

        // Observe LiveData to update the UI
        viewModel.prediction.observe(this, Observer { prediction ->
            findViewById<TextView>(R.id.txtPrediction).text = "Best Planting Date: $prediction"
        })

        // Call getOptimalPlantingDate to trigger the calculation
        viewModel.getOptimalPlantingDate(25.0f, 80.0f, 2.0f)

        findViewById<Button>(R.id.btnBackToMain).setOnClickListener {
            finish()  // Closes current activity and returns to MainActivity
        }
    }
}
