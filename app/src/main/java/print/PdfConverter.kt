package android.print;

import android.annotation.TargetApi
import android.content.Context
import android.net.Uri
import android.net.http.SslError
import android.os.ParcelFileDescriptor
import android.util.Log
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@TargetApi(19)
class PdfConverter @Inject constructor(
    private val context: Context,
    private val errorCommunication: (String) -> Unit
) {

    private val defaultPrintAttributes: PrintAttributes by lazy {
        PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.NA_GOVT_LETTER)
            .setResolution(PrintAttributes.Resolution("RESOLUTION_ID", "RESOLUTION_ID", 1600, 1600))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()
    }

    private var printAttributes: PrintAttributes? = null

    fun printAttributes(printAttributes: PrintAttributes): PdfConverter = apply {
        this.printAttributes = printAttributes
    }

    suspend fun convert(htmlFile: File, pdfFile: File) {
        val mimeType = "text/html"
        val encoding = "UTF-8"
        val url = Uri.fromFile(htmlFile).toString()
        withContext(Dispatchers.Main) {
            suspendCoroutine<Unit> { continuation ->
                WebView(context).apply {
                    this.settings.javaScriptEnabled = true
                    this.settings.allowFileAccess = true
                    this.settings.useWideViewPort = true
                    this.settings.loadWithOverviewMode = true
                    this.settings.setSupportZoom(true)
                    this.settings.builtInZoomControls = true
                    this.settings.displayZoomControls = false
                    this.settings.loadsImagesAutomatically = true
                    this.settings.allowContentAccess = true
                    webViewClient = WebViewClientImpl(pdfFile, continuation)
                }.loadUrl(url)
            }
        }
    }

    private fun File.outputFileDescriptor(): ParcelFileDescriptor? =
        try {
            createNewFile()
            ParcelFileDescriptor.open(
                this,
                ParcelFileDescriptor.MODE_TRUNCATE or ParcelFileDescriptor.MODE_READ_WRITE
            )
        } catch (e: Exception) {
            null
        }

    companion object {
        fun from(context: Context, errorCommunication: (String) -> Unit): PdfConverter =
            PdfConverter(context, errorCommunication)
    }

    private inner class WebViewClientImpl(
        private val file: File,
        private val continuation: Continuation<Unit>
    ) : WebViewClient() {
        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            errorCommunication("Yes")
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            errorCommunication("YES")
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            errorCommunication("YES")
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)
            errorCommunication("YES")
        }


        override fun onPageFinished(webview: WebView, url: String) {

//            CookieManager.getInstance().setAcceptThirdPartyCookies(webview, true);
            webview.createPrintDocumentAdapter()?.run {
                onLayout(
                    null,
                    printAttributes ?: defaultPrintAttributes,
                    null,
                    object : PrintDocumentAdapter.LayoutResultCallback() {},
                    null
                )
                onWrite(
                    arrayOf(PageRange.ALL_PAGES),
                    file.outputFileDescriptor(),
                    null,
                    object : PrintDocumentAdapter.WriteResultCallback() {
                        override fun onWriteCancelled() {
                            super.onWriteCancelled()
                            Log.e("PDF Converter", "onWriteCancelled")
                            errorCommunication("Yes")
                            continuation.resume(Unit)
                        }

                        override fun onWriteFailed(error: CharSequence?) {
                            super.onWriteFailed(error)
                            Log.e("PDF Converter", "onWriteFailed")

//                            continuation.resumeWithException(Exception(error.toString()))
                            errorCommunication("Yes")
                        }

                        override fun onWriteFinished(pages: Array<out PageRange>?) {
                            super.onWriteFinished(pages)
                            Log.e("PDF Converter", "onWriteFinished")
                            continuation.resume(Unit)
                        }
                    }
                )
            }
        }
    }
}