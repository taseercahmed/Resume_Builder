package com.semixtech.cv_resume_builder.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleOwner




class MyApplication : Application() {

    var activity: Activity? = null
    var lifecycleOwner: LifecycleOwner? = null
//    private var retrofitServices: RetrofitServices? = null
//    private var mDB: AppDatabase? = null
//    var showTokenExpireDialog: AlertDialog? = null
//    var serverMaintenanceDialog: AlertDialog? = null


    companion object {
        lateinit var mInstance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)


//        startKoin {
//            androidContext(this@MyApplication)
//            modules(
//                listOf(
//
//                    viewmodelmodule,
//
//
//                    ))
//        }
    }

    fun setLifeCylceOwner(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

//    fun showTokenExpiredDialog() {
//        if (activity != null) {
//            if (showTokenExpireDialog != null  && !showTokenExpireDialog!!.isShowing) {
//                val alertDialog1 = AlertDialog.Builder(
//                    activity!!
//                )
//                alertDialog1.setCancelable(false)
//                val inflater1 = activity!!.layoutInflater
//                val alert_dialog_view1: View =
//                    inflater1.inflate(R.layout.dialog_token_expire, null)
//                alertDialog1.setView(alert_dialog_view1)
//                showTokenExpireDialog = alertDialog1.show()
//                showTokenExpireDialog!!.getWindow()!!
//                    .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                showTokenExpireDialog!!.dismiss()
//                val logout = alert_dialog_view1.findViewById<Button>(R.id.bt_close)
//                logout.setOnClickListener {
//                    logout()
//                }
//            }
//
//            if (showTokenExpireDialog != null && !showTokenExpireDialog!!.isShowing) {
//
//                try {
//                    showTokenExpireDialog!!.show()
//                } catch (e: WindowManager.BadTokenException) {
//                    //use a log message
//                    logout()
//                }
//            }
//        }
//    }
//
//    fun showServerMaintenanceDialog() {
//        if (activity != null) {
//            if (serverMaintenanceDialog == null) {
//                val alertDialog = AlertDialog.Builder(activity!!)
//                alertDialog.setCancelable(false)
//                val inflator = activity!!.layoutInflater
//                val alertDialogView: View = inflator.inflate(
//                    R.layout.dialog_server_maintenance,
//                    null
//                )
//                alertDialog.setView(alertDialogView)
//                serverMaintenanceDialog = alertDialog.show()
//                serverMaintenanceDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                serverMaintenanceDialog!!.dismiss()
//                val exit = alertDialogView.findViewById<Button>(R.id.btn_exit)
//                exit.setOnClickListener {
//                    if (serverMaintenanceDialog != null && serverMaintenanceDialog!!.isShowing) {
//                        serverMaintenanceDialog!!.dismiss()
//                        activity!!.finishAffinity()
//                    }
//                }
//            }
//            if (serverMaintenanceDialog != null && !serverMaintenanceDialog!!.isShowing) {
//                serverMaintenanceDialog!!.show()
//            }
//        }
//    }

//    fun logout() {
//        if (showTokenExpireDialog != null && showTokenExpireDialog!!.isShowing) {
//            showTokenExpireDialog!!.dismiss()
//        }
//
//        SharedHelper.putKey(this, AppConstants.AUTH_TOKEN, "")
//        SharedHelper.putKey(this, AppConstants.business_logo, "")
//        SharedHelper.putKey(this, AppConstants.business_email, "")
//        SharedHelper.putKey(this, AppConstants.business_name, "")
//        var intent = Intent(this, LoginActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent)
//    }

    fun setCurrentActivity(activity: Activity?) {
        this.activity = activity


    }

//    @Synchronized
//    fun getRetrofirService(): RetrofitServices? {
//        val retrofit: Retrofit = ApiClientAuth.client!!
//        retrofitServices = retrofit.create(RetrofitServices::class.java)
//        return retrofitServices
//    }
//
//    fun getLocalDb(context: Context): AppDatabase {
//        mDB = AppDatabase.getInstance(context)
//        return mDB!!
//    }
}