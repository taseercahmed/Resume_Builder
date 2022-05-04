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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Document
import java.io.*
import java.util.*

import javax.security.auth.callback.Callback


class PreviewFragment : BaseFramnet<FragmentPreviewBinding>() {
    lateinit var viewModel: MainViewModel

    var invoice_id: String? = ""

    companion object {
        fun newInstance(invoice_id: String): PreviewFragment {
            val args = Bundle()
            args.putString("invoice_id", invoice_id)
            val fragment = PreviewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {
//        viewModel = ViewModelProviders.of(this!!)[MainViewModel::class.java]
//        dataBinding!!.pdfView.fromFile(viewModel.pdfFile.value).load()


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//        if (requireArguments().containsKey("invoice_id"))
//        {
//            invoice_id  = requireArguments().getString("invoice_id")
//            //  GetPdf()
//        }

    }

    override fun getlayout(): Int {
        return R.layout.fragment_preview
    }
    fun GetPdf()
    {
        if((activity as HomeActivity).isConnectedToInternet()) {
            //   customProgressDialog!!.show()
           // showCustomDialogueNewDesign("Please Wait...")
            Log.i("Tag", "GetPdf: ${viewModel!!.pdfFile.value?.absolutePath}")
            viewModel!!.pdfFile.observe(viewLifecycleOwner, {
                hideCustomDialogueNewDesign()
                dataBinding!!.pdfView.visibility = View.VISIBLE
                dataBinding!!.htmlWebView.visibility = View.GONE

                Log.e("PreviewEstimateFragment", "Showing PDF:" + it.path)
            })
        }



    }

//    fun GetPdf()
//    {
//        if((activity as HomeActivity).isConnectedToInternet()) {
//            //   customProgressDialog!!.show()
//            showCustomDialogueNewDesign("Please Wait...")
//            invoiceViewmodel!!.pdfFile.observe(viewLifecycleOwner, {
//                if (null != it && (it!!.status == Status.ERROR || it!!.status == Status.SUCCESS)) {
//                    //       customProgressDialog!!.dismiss()
//                    hideCustomDialogueNewDesign()
//                }
//
//                if (it!!.status == Status.ERROR) {
//                    showErrorSnakbar(it!!.message!!)
//                }
//
//                if (it!!.status == Status.SUCCESS && it!!.data != null && it!!.message == null) {
//
//                    if (it.data!!.showPDF) {
//                        dataBinding!!.pdfView.visibility = View.VISIBLE
//                        dataBinding!!.htmlWebView.visibility = View.GONE
////                        val client = OkHttpClient()
////                        val request =
////                           Builder.url(BuildConfig.BASE_URL + it.data!!.pdf_url).build()
//                        Log.e("PreviewEstimateFragment", "Showing PDF:" + it.data!!.pdf_url)
//
////                        client.newCall(request).enqueue(object : Callback {
////                            @Throws(IOException::class)
////                            fun onResponse(call: Call, response: Response) {
////                                if (!response.isSuccessful) {
////                                    throw IOException("Failed to download file: $response")
////                                }
////                                var job = runBlocking(Dispatchers.Main) {
////                                    launch(Dispatchers.Main) {
////                                        val stream = ByteArrayInputStream(response.body?.bytes())
//////                                    var pdf_file = copyStreamToFile(stream)
////                                        dataBinding!!.pdfView.fromStream(stream)
////                                            .spacing(0)
////                                            .load()
////                                    }
////                                }
////                            }
////
////                            fun onFailure(call: Call, e: IOException) {
////                                e.printStackTrace()
////                                Log.e("TAG", "Failed to load PDF")
////
////                            }
////                        })
//                    } else {
//                        dataBinding!!.pdfView.visibility = View.GONE
//                        dataBinding!!.htmlWebView.visibility = View.VISIBLE
//                        dataBinding!!.htmlWebView.settings.builtInZoomControls = true
//                        dataBinding!!.htmlWebView.settings.displayZoomControls = false
//                        dataBinding!!.htmlWebView.getSettings().setLoadWithOverviewMode(true);
//                        dataBinding!!.htmlWebView.getSettings().setUseWideViewPort(true)
//                        dataBinding!!.htmlWebView.setInitialScale(30)
//                        dataBinding!!.htmlWebView.loadUrl(BuildConfig.BASE_URL + it.data!!.html)
//                        Log.e("PreviewEstimateFragment", "Showing HTML:" + it.data!!.html)
//
//                    }
//
//
//
//
//
//
//                }
//            })
//        }
//    }

    fun copyStreamToFile(inputStream: InputStream): File {
            var outPutDir: File = requireContext().getFilesDir()
            val outputFile = File.createTempFile("temppdf", ".pdf", outPutDir)
            inputStream.use { input ->
                val outputStream = FileOutputStream(outputFile)
                outputStream.use { output ->
                    val buffer = ByteArray(4 * 1024) // buffer size
                    while (true) {
                        val byteCount = input.read(buffer)
                        if (byteCount < 0) break
                        output.write(buffer, 0, byteCount)
                    }
                    output.flush()
                }
            }
            return outputFile
        }

    }
