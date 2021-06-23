package dev.dmayr.taptovibrate

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.dmayr.taptovibrate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonShort.setOnClickListener {
            vibratePhoneShort()
        }
        binding.buttonLong.setOnClickListener {
            vibratePhoneLong()
        }
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneShort() {
        val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibration.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
        } else vibration.vibrate(150)
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneLong() {
        val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibration.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE))
        } else vibration.vibrate(250)
    }
}