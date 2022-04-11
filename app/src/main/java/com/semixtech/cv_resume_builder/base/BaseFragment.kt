package com.semixtech.cv_resume_builder.base

import android.app.Dialog
import android.content.Context
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.helper.CustomDialog
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseFramnet<DB : ViewDataBinding?> : Fragment(),
    LifecycleOwner {
    var dataBinding: DB? = null
    protected var customProgressDialog: CustomDialog? = null
    protected var mContext: Context? = null
    var filter: IntentFilter? = null
    var gpsintentfilter: IntentFilter? = null

    private var mDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        callGrbadgeCollection()
        dataBinding = DataBindingUtil.inflate(inflater, getlayout(), container, false)
        mContext = activity
        customProgressDialog = CustomDialog(activity)
        mDialog = Dialog(requireContext()!!)
        mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog!!.setContentView(R.layout.custom_dialog_design)
        mDialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog!!.setCancelable(false)
        OnCreateView(inflater, savedInstanceState)
        MyApplication.mInstance.setLifeCylceOwner(this)
        return dataBinding!!.root

    }

    fun isConnectedToInternet(): Boolean {
        val connectivity =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivity.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnectedOrConnecting) {

        } else {
            showErrorSnakbar(getString(R.string.check_internet))
        }
        return networkInfo != null && networkInfo.isConnectedOrConnecting

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        callGrbadgeCollection()
    }

    override fun onDestroy() {
        super.onDestroy()
//        callGrbadgeCollection()
    }



    fun formatDate(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd").format(date)
    }

    fun convertDate(dateString: String): Date? {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateString)
    }

    abstract fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?)
    abstract fun getlayout(): Int
    fun GetDataBinding(): DB? {
        return dataBinding
    }

    fun showCustomDialogueNewDesign(msg: String) {
        if (mDialog!!.isShowing) {
            UpdateCustomDialogueNewDesign(msg)
        } else {
         //   mDialog!!.messagetv!!.setText(msg)
            mDialog!!.setCancelable(false)
            mDialog!!.show()
        }
//        callGrbadgeCollection()
    }

    fun UpdateCustomDialogueNewDesign(msg: String) {
       // mDialog!!.messagetv!!.setText(msg)
//        callGrbadgeCollection()
    }

    fun hideCustomDialogueNewDesign() {
        mDialog!!.dismiss()
//        callGrbadgeCollection()
    }

    public fun showErrorSnakbar(Error: String) {
        val snackbar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content), Error,
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackbar.setActionTextColor(Color.RED)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.WHITE)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.RED)
        textView.textSize = 15f
        snackbar.show()
    }

    fun roundValue(value: Double): Double {
        return BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    public fun showSucessSnakbar(Error: String) {
        val snackbar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content), Error,
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackbar.setActionTextColor(Color.WHITE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(resources.getColor(R.color.paid_green))
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 15f
        snackbar.show()
    }

    open fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }

   }
