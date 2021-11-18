package com.hunseong.customview_seekbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.widget.SeekBar
import kotlin.math.abs

@SuppressLint("AppCompatCustomView")
class SeekBarWithHint @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : SeekBar(context, attrs, defStyle) {

    private val seekBarHintPaint: Paint = Paint()
    private val seekBarBoxPaint: Paint = Paint()
    private val hintTextColor: Int
    private val hintTextSize: Float

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SeekBarWithHint, 0, 0)

        try {
            hintTextColor = typedArray.getColor(R.styleable.SeekBarWithHint_hint_text_color, 0)
            hintTextSize = typedArray.getDimension(R.styleable.SeekBarWithHint_hint_text_size, 0F)
            seekBarHintPaint.color = hintTextColor
            seekBarHintPaint.textSize = hintTextSize
            seekBarHintPaint.textAlign = Paint.Align.CENTER

            seekBarBoxPaint.color = Color.BLACK

        } finally {
            typedArray.recycle()
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val thumbX: Float = if(progress!=0) (progress.toFloat() / max) * (width-60) + 20 else 20F

        val rect = Rect(20, 0, thumbX.toInt()+1, height/2)

        val textRect = Rect()
        val textPaint = Paint()

        textPaint.getTextBounds(progress.toString(), 0, progress.toString().length, textRect)

        canvas?.drawText(progress.toString(), thumbX, height.toFloat(), seekBarHintPaint)
        canvas?.drawRect(rect, seekBarBoxPaint)
    }

}

fun dpToPx(context: Context, dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
}