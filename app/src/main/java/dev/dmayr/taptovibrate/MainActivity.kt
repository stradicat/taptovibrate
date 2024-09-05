package dev.dmayr.taptovibrate

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import dev.dmayr.taptovibrate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var textView: TextView
    private var vibrationLong: Long = 180
    private var vibrationShort: Long = 80

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textView = binding.textView
        binding.apply {
            listOf(
                textView,
                buttonShort.setOnClickListener {
                    vibratePhoneShort()
                    // onFinish(textView)
                },
                buttonLong.setOnClickListener {
                    vibratePhoneLong()
                    // onFinish(textView)
                }
            )
        }
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneShort() {
        val vibration = getSystemService<Vibrator>()
        if (vibration!!.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibration.vibrate(
                    VibrationEffect.createOneShot(
                        vibrationShort,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // 80 minimum
            } else vibration.vibrate(vibrationShort) // 80 minimum
            textView.setTextColor(Color.GREEN)
            textView.text = getString(R.string.vibration_short)
        } else noVibratorWarning(textView, this)
    }

    @Suppress("DEPRECATION")
    private fun vibratePhoneLong() {
        val vibration = getSystemService<Vibrator>()
        if (vibration!!.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibration.vibrate(
                    VibrationEffect.createOneShot(
                        vibrationLong,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // 160 min
            } else vibration.vibrate(vibrationLong) // 160 minimum
            textView.setTextColor(Color.RED)
            textView.text = getString(R.string.vibration_long)
        } else noVibratorWarning(textView, this)
    }
}

//fun onTick(duration: Duration) {
//// TODO: implement method to add a timer
//}

//fun onFinish(view: View) {
//    view.visibility = View.INVISIBLE
//}

private fun noVibratorWarning(textView: TextView, context: Context) {
    textView.setTextColor(Color.RED)
    textView.text = context.getString(R.string.no_vibrator_device_detected)
}
