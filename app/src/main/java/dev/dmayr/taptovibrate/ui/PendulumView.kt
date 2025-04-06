package dev.dmayr.taptovibrate.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class PendulumView(
    context: Context, attributes: AttributeSet? = null, defStyleAttribute: Int = 0
) : View(context, attributes, defStyleAttribute) {

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private var angle = 0f
    private var maxAngle = 45f // Maximum angle in degrees
    private var animator: ObjectAnimator? = null
    private var bpm = 60

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var centerX = width / 2f
        var centerY = height.toFloat()
        val needleLength = height * 0.8f // adjust needle length to needs

        val radians = Math.toRadians(angle.toDouble()).toFloat()

        val endX = centerX + needleLength * sin(radians)
        val endY = centerY + needleLength * cos(radians)

        canvas.drawLine(centerX, centerY, endX, endY, paint)
    }

    fun setBpm(beatsPerMinute: Int) {
        bpm = beatsPerMinute // global 'bpm' = local 'beatsPerMinute'
        animatePendulum()
    }

    private fun animatePendulum() {
        animator?.cancel() // Cancel any existing animation
        val duration = (60000.0 / bpm).toLong() // Calculate duration based on BPM
        animator = ObjectAnimator.ofFloat(this, "angle", -maxAngle, maxAngle, -maxAngle).apply {
            this.duration = duration
            repeatCount = ObjectAnimator.INFINITE
            interpolator = LinearInterpolator()
            start()
        }
    }

    // Called by 'ObjectAnimator'
    fun setAngle(angle: Float) {
        this.angle = angle
        invalidate() // redraw the view
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.cancel() // cancel animation when the view is detached
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val desiredWidth = 200 // Set your desired width
        val desiredHeight = 300 // Set your desired height

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize)
            else -> desiredWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }
}
