package dev.dmayr.taptovibrate

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.dmayr.taptovibrate.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        textView = findViewById<TextView>(R.id.textView)
        binding.apply { textView.text }


        binding.buttonShort.setOnClickListener {
            vibratePhoneShort()
            // onFinish(textView)
        }
        binding.buttonLong.setOnClickListener {
            vibratePhoneLong()
            // onFinish(textView)

        }
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneShort() {
        val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibration.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibration.vibrate(
                    VibrationEffect.createOneShot(
                        80,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // 80 min
            } else vibration.vibrate(80) // 80 min
            textView.setTextColor(Color.GREEN)
            textView.text = "bzzz"
        } else noVibratorWarning(textView)
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneLong() {
        val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibration.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibration.vibrate(
                    VibrationEffect.createOneShot(
                        180,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // 160 min
            } else vibration.vibrate(180) // 160 min
            textView.setTextColor(Color.RED)
            textView.text = "B Z Z Z"
        } else noVibratorWarning(textView)
    }
}

fun onTick(duration: Duration) {
// TODO: implement method to add a timer
}

fun onFinish(view: View) {
    view.visibility = View.INVISIBLE
}

private fun noVibratorWarning(textView: TextView) {
    textView.setTextColor(Color.RED)
    textView.text = "No vibrator device detected"
}