package com.example.weatherapp.util.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.example.weatherapp.R

class ImageLoadView : RelativeLayout {

    var imageView: ImageView
    var progressBar: ProgressBar

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        inflate(context, R.layout.view_image_load, this)

        imageView = findViewById(R.id.ivImageLoad)
        progressBar = findViewById(R.id.pbImageLoad)
    }

    fun startLoading() {
        imageView.visibility = GONE
        progressBar.visibility = VISIBLE
    }

    fun completeLoading() {
        progressBar.visibility = GONE
        imageView.visibility = VISIBLE
    }

}