package dev.dmayr.taptovibrate

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.dmayr.taptovibrate.ui.theme.TapToVibrateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TapToVibrateTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TapToVibrate(modifier = Modifier)
                }
            }
        }
    }
}

private var vibrationLong: Long = 160
private var vibrationShort: Long = 80

@Composable
fun TapToVibrate(modifier: Modifier = Modifier) {

}

@RequiresApi(Build.VERSION_CODES.M)
@Suppress("DEPRECATION")
private fun vibratePhoneShort(context: Context) {
    val vibration = context.getSystemService(Vibrator::class.java)
    if (vibration!!.hasVibrator()) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibration.vibrate(
                VibrationEffect.createOneShot(
                    vibrationShort,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            ) // 80 minimum
        } else vibration.vibrate(vibrationShort) // 80 minimum
//            textView.setTextColor(Color.GREEN)
//            textView.text = getString(R.string.vibration_short)
//        } else noVibratorWarning(textView, this)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Suppress("DEPRECATION")
private fun vibratePhoneLong(context: Context) {
    val vibration = context.getSystemService(Vibrator::class.java)
    if (vibration!!.hasVibrator()) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibration.vibrate(
                VibrationEffect.createOneShot(
                    vibrationLong,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            ) // 160 min
        } else vibration.vibrate(vibrationLong) // 160 minimum
//            textView.setTextColor(Color.RED)
//            textView.text = getString(R.string.vibration_long)
//        } else noVibratorWarning(textView, this)
    }
}

//fun onTick(duration: Duration) {
//// TODO: implement method to add a timer
//}

fun onFinish(view: View) {
    view.visibility = View.INVISIBLE
}

fun noVibratorWarning(textView: TextView, context: Context) {
//    textView.setTextColor(Color.Red)
//    textView.text = context.getString(R.string.no_vibrator_device_detected)
}


@Preview(showBackground = true)
@Composable
fun TapToVibratePreview() {
    TapToVibrateTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TapToVibrate(modifier = Modifier)
        }
    }
}
