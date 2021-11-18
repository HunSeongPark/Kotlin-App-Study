package com.hunseong.customview_textview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomLoginButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val bg: LinearLayout
    private val symbol: ImageView
    private val text: TextView


    init {
        val infService: String = Context.LAYOUT_INFLATER_SERVICE
        val li: LayoutInflater = context.getSystemService(infService) as LayoutInflater
        val view: View = li.inflate(R.layout.welcome_login_button, this, false)
        addView(view)

        bg = findViewById(R.id.bg)
        symbol = findViewById(R.id.symbol)
        text = findViewById(R.id.text)

        getAttrs(attrs)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginButton)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginButton, defStyle, 0)
        setTypeArray(typedArray)
    }

    @SuppressLint("ResourceAsColor")
    private fun setTypeArray(typedArray: TypedArray) {
        val bg_resID: Int =
            typedArray.getColor(R.styleable.LoginButton_bg, 0)
        bg.setBackgroundColor(bg_resID)

        val symbol_resID: Int =
            typedArray.getResourceId(R.styleable.LoginButton_symbol, R.drawable.kakao)
        symbol.setBackgroundResource(symbol_resID)

        val textColor: Int =
            typedArray.getColor(R.styleable.LoginButton_textColor, 0)
        text.setTextColor(textColor)

        val text_string: String? =
            typedArray.getString(R.styleable.LoginButton_text)
        text.text = text_string

        typedArray.recycle()
    }
}