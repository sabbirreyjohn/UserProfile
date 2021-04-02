package com.androidrey.custombutton


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.use
import androidx.core.view.children
import com.androidrey.custombutton.databinding.TheButtonBinding


class TheButton @JvmOverloads constructor(
    c: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayout(c, attrs, defStyleAttr) {
    init {
        var binding = TheButtonBinding.inflate(LayoutInflater.from(c), this)
        setPadding(
            c.resources.getDimension(R.dimen.ten_dp).toInt(),
            c.resources.getDimension(R.dimen.ten_dp).toInt(),
            c.resources.getDimension(R.dimen.ten_dp).toInt(),
            c.resources.getDimension(R.dimen.ten_dp).toInt()
        )
        background = c.getDrawable(R.drawable.button_selector)
        c.theme.obtainStyledAttributes(attrs, R.styleable.TheButton, 0, 0).use {
            isEnabled = it.getBoolean(R.styleable.TheButton_android_enabled, isEnabled)
        }
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.TheButton, 0, 0)
        if (attrsArray.getResourceId(R.styleable.TheButton_icon_image, 0) != 0) {
            binding.image.setImageDrawable(
                attrsArray.getDrawable(
                    R.styleable.TheButton_icon_image
                )
            )
        } else {
            binding.rlLeft.visibility = GONE
        }
        binding.title.text = attrsArray.getString(R.styleable.TheButton_title)
        binding.subtitle.text = attrsArray.getString(R.styleable.TheButton_subtitle)
        if (attrsArray.getString(R.styleable.TheButton_title).isNullOrEmpty())
            binding.rlRight.visibility = GONE
        else if (attrsArray.getString(R.styleable.TheButton_subtitle).isNullOrEmpty())
            binding.subtitle.visibility = GONE

        attrsArray.recycle()
    }


    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        children.forEach { it.isEnabled = enabled }
    }
}