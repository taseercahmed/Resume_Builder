package com.semixtech.cv_resume_builder.helper

import android.app.ProgressDialog
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.Window
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.semixtech.cv_resume_builder.R


public class CustomDialog
    (context: Context?) : ProgressDialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        isIndeterminate = true
        val msg: String = "Please wait..."
        setMessage(msg)
        val drawable: Drawable = ProgressBar(context).indeterminateDrawable.mutate()
        drawable.setColorFilter(
            ContextCompat.getColor(context!!, R.color.purple_200),
            PorterDuff.Mode.SRC_IN
        )
        setIndeterminateDrawable(drawable)
    }
}

