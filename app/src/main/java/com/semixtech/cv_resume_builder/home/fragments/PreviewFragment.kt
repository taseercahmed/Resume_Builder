package com.semixtech.cv_resume_builder.home.fragments


import android.accessibilityservice.GestureDescription
import android.app.DownloadManager
import android.app.people.ConversationStatus
import android.content.ContentValues.TAG
import android.net.Uri

import android.os.Bundle

import android.telecom.Call
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.textclassifier.ConversationActions
import android.view.textclassifier.TextLanguage

import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.semixtech.cv_resume_builder.BuildConfig
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseFramnet
import com.semixtech.cv_resume_builder.databinding.FragmentPreviewBinding

import com.semixtech.cv_resume_builder.helper.Status

import com.semixtech.cv_resume_builder.home.activities.HomeActivity
import com.semixtech.cv_resume_builder.kotlinwork.viewmodel.MainViewModel
import kotlinx.coroutines.*
import org.w3c.dom.Document
import java.io.*
import java.util.*

import javax.security.auth.callback.Callback


class PreviewFragment : BaseFramnet<FragmentPreviewBinding>() {
    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {
    }

    override fun getlayout(): Int {
        return R.layout.fragment_preview
    }

    fun getTemplatePreview(pdfFile: File?) {
        pdfFile?.let { pdf ->
            val fileSize: Double = java.lang.String.valueOf(pdf.length() / 1024).toDouble()
            Log.e("TAG", "getTemplatePreview: size = $fileSize")


            if (activity != null) {
                if (!requireActivity().isFinishing && fileSize > 0) {
                    Log.e("getTemplatePreview::", "Called for preview")
                    GlobalScope.launch(Dispatchers.IO) {
                        withContext(Dispatchers.Main) {
                            dataBinding!!.pdfView.visibility = View.INVISIBLE
                        }
                    }
                    GlobalScope.launch(Dispatchers.IO) {
                        launch(Dispatchers.IO) {
                            withTimeout(60000L) {
                                try {
                                    Log.e(
                                        "Loading Status",
                                        "loading ------------> Going to load"
                                    )

                                    withContext(Dispatchers.Main) {
                                        if (!requireActivity().isFinishing) {
                                            dataBinding!!.pdfView.fromFile(pdf)
                                                .onError {
                                                    Log.e(
                                                        "Preview Status",
                                                        "Error ------------> Error while previewing PDF"
                                                    )
                                                }.onLoad {

                                                    dataBinding!!.pdfView.visibility = View.VISIBLE
//
//                                    Toast.makeText(requireContext(), "Loaded", Toast.LENGTH_SHORT)
//                                        .show()
                                                }.load()
                                        }
                                    }
                                } catch (e: Exception) {
                                    Log.e(
                                        " withTimeout exception ",
                                        "hi ----> " + e.localizedMessage
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Log.e("TAG", "getTemplatePreview: Invalid PDF File")
                }
            }
        }
    }

}
