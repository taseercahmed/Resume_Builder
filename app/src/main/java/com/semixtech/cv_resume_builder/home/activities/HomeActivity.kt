package com.semixtech.cv_resume_builder.home.activities

import android.Manifest
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.print.PdfConverter
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseActivity
import com.semixtech.cv_resume_builder.databinding.ActivityHomeBinding
import com.semixtech.cv_resume_builder.db.Entity.*
import com.semixtech.cv_resume_builder.helper.*
import com.semixtech.cv_resume_builder.home.fragments.MainHomefragment
import com.semixtech.cv_resume_builder.home.fragments.PreviewFragment
import com.semixtech.cv_resume_builder.home.fragments.SavedFragment
import com.semixtech.cv_resume_builder.home.templates.Template1
import com.semixtech.cv_resume_builder.kotlinwork.viewmodel.MainViewModel
import kotlinx.coroutines.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class HomeActivity : BaseActivity<ActivityHomeBinding>(), ClickHandler,
    ViewPager.OnPageChangeListener {

    lateinit var adapter: SectionsPagerAdapter
    var tabglobal: TabLayout.Tab? = null
    lateinit var viewModel: MainViewModel
    private lateinit var htmlFile: File
    var converter: PdfConverter? = null
    var pdfFileGenerationJob: Job? = null
    private var backgroundTaskList: MutableList<Job?> = arrayListOf<Job?>(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
    var previousJobAnalyser: Job? = null
    var pdfGenerationJobFromWebView: Job? = null
    private var templateDefaultModel: TemplateDefaultModel = TemplateDefaultModel()

    fun invoiceCommunicationEditScreen() {
        getPreview()
    }

    private fun getPreview() {
        if (viewModel.pdfFile.value != null) {
            if (viewModel.pdfFile.value!!.exists()) {
                Log.e("TAG", "getPreview: ${viewModel.pdfFile.value!!.absolutePath}")
                //  viewModel.pdfFile.value!!.delete()
            }
        }
        if (this@HomeActivity::htmlFile.isInitialized) {
            if (htmlFile.exists()) {
                htmlFile.delete()
            }
        }
        var user: UserEntity = UserEntity("", "", "", "", "", "", "", "")
        if (viewModel.Users.value != null && viewModel.Users.value!!.size > 0) {
            user = viewModel.Users.value!!.get(0)
        }
        var edu: UserEducationEntity = UserEducationEntity(0, "", "", "", "", "")
        if (viewModel.Users.value != null && viewModel.Users.value!!.size > 0) {
            edu = viewModel.UserEducation.value!!.get(0)
        }
        var history: UserHistoryEntity = UserHistoryEntity(0, "", "", "", "", "", "", "")
        if (viewModel.UsersHistory.value != null && viewModel.UsersHistory.value!!.size > 0) {
            history = viewModel.UsersHistory.value!!.get(0)
        }
        var skill: UserSkillsEntity = UserSkillsEntity(0, "")
        if (viewModel.UserSkills.value != null && viewModel.UserSkills.value!!.size > 0) {
            skill = viewModel.UserSkills.value!!.get(0)
        }
        var summary: UserSummaryEntity = UserSummaryEntity(0, "")
        if (viewModel.UserSummary.value != null && viewModel.UserSummary.value!!.size > 0) {
            summary = viewModel.UserSummary.value!!.get(0)
        }


        templateDefaultModel = TemplateDefaultModel(
            user = user!!,
            workhistory = history!!,
            userEducationEntity = edu!!,
            userSkillsEntity = skill!!,
            userSummaryEntity = summary!!
        )
        val htmlText: String = fragmentPreviewInvoiceForInvoice()

        Log.e("Invoice Converted", "hi-->$htmlText")
        val htmlFilePath = saveHtmlTextToFile(htmlText)

        GlobalScope.launch(Dispatchers.Main) {
//                Toast.makeText(context!!, "HTML Created", Toast.LENGTH_SHORT).show()
            createPdfFromHtml(htmlFilePath)
        }
    }


    @Suppress("DEPRECATION")
    fun createPdfFromHtml(htmlFile: File) {
        Log.e("TAG", "createPdfFromHtml:Started...")

        viewModel.pdfFile.value =
            File(context.filesDir.path, "/ResumeActualPDF.pdf")
        Log.e("Tag", "createPdfFromHtml: ${viewModel.pdfFile.value!!.absolutePath}")

        //  Log.e("TAG", "createPdfFromHtml:124646 ${viewModel.pdfFile.value!!.absolutePath} ")
        backgroundTaskList[3] = pdfFileGenerationJob
        previousJobAnalyser = GlobalScope.launch(Dispatchers.IO) {
//            pdfFileGenerationJob!!.join()
////            Toast.makeText(context!!, "Cancling previous one", Toast.LENGTH_SHORT).show()
            if (pdfGenerationJobFromWebView != null) {
                pdfGenerationJobFromWebView!!.cancel()
            }
            pdfGenerationJobFromWebView = GlobalScope.launch(Dispatchers.Unconfined) {
                suspend {
                    if (viewModel.pdfFile.value != null) {
                        converter!!.convert(htmlFile, viewModel.pdfFile.value!!)
                    } else {
                        Log.e("TAG", "createPdfFromHtml:3 ")
                    }

                }.invoke()
            }

        }
        backgroundTaskList[4] = previousJobAnalyser
        backgroundTaskList[5] = pdfGenerationJobFromWebView

        Log.e("TAG", "createPdfFromHtml: backGround task waiting for task completion")
        backgroundTaskList[6] = GlobalScope.launch(Dispatchers.IO)
        {
            previousJobAnalyser!!.join()
            if (pdfGenerationJobFromWebView != null) {
                pdfGenerationJobFromWebView!!.join()
                val fragment = PreviewFragment()
                // fragment.getTemplatePreview(viewModel.pdfFile.value!!)
                Log.e("TAG", "createPdfFromHtml: PDF generation completed..." + fragment)

            } else {
                createPdfFromHtml(htmlFile)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun saveHtmlTextToFile(htmlText: String): File {

        htmlFile = File(context.filesDir.path, "/ResumeActual.html")

        try {
            val out = FileOutputStream(htmlFile)
            val data = htmlText.toByteArray()
            out.write(data)
            out.close()
            Log.e("TAG", "File Save : " + htmlFile.path)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return htmlFile
    }

    private fun fragmentPreviewInvoiceForInvoice(): String {
        var s = generateTemplate("1")
        Log.e("TAG", "Gnerate tamplate : " + s)
        return s
    }

    private fun generateTemplate(templateId: String): String {
        when (templateId) {
            "1" -> {
                return Template1().convert(templateDefaultModel)
            }
//            "2" -> {
//                return Template2().convert(templateDefaultModel)
//            }
//            "3" -> {
//                return Template3().convert(templateDefaultModel)
//            }
//            "4" -> {
//                return Template4().convert(templateDefaultModel)
//            }
//            "5" -> {
//                return Template5().convert(templateDefaultModel)
//            }
//            "6" -> {
//                return Template6().convert(templateDefaultModel)
//            }
        }
        return ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SectionsPagerAdapter(supportFragmentManager)
        setupViewPager(dataBinding!!.viewPager)
        initSetting()

        viewModel = ViewModelProviders.of(this!!)[MainViewModel::class.java]
        converter = PdfConverter.from(this) { error -> errorCommunication(error) }
        invoiceCommunicationEditScreen()

        //RunTime Permission
        //RunTime Permission
        val dialogMultiplePermissionsListener: MultiplePermissionsListener =
            DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(this)
                .withTitle(R.string.media_access_title)
                .withMessage(R.string.permission_description)
                .withButtonText(android.R.string.ok)
                .withIcon(R.drawable.ic_launcher_background)
                .build()

        val permissionsListener: MultiplePermissionsListener =
            object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        // do you work now
                        Log.d("TAG", "OK")
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied()) {
                        // permission is denied permenantly, navigate user to app settings
                        gotoSettingsScreen()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }

        val compositePermissionsListener: MultiplePermissionsListener =
            CompositeMultiplePermissionsListener(
                permissionsListener,
                dialogMultiplePermissionsListener
            )

        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )
            .withListener(compositePermissionsListener)
            .onSameThread()
            .check()
    }

    private fun gotoSettingsScreen() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

    }

    private fun errorCommunication(error: String) {
        Log.e("TAG", "errorCommunication:${error} ")
        getPreview()
    }

    private fun initSetting() {
        dataBinding!!.tabLayoutHome!!.setupWithViewPager(dataBinding!!.viewPager)

        dataBinding!!.viewPager!!.addOnPageChangeListener(this)
        dataBinding!!.viewPager!!.offscreenPageLimit = 1
        tabglobal = dataBinding!!.tabLayoutHome.getTabAt(0)!!
        dataBinding!!.tabLayoutHome.getTabAt(0)!!.setIcon(R.drawable.ic_edit__1_)
        dataBinding!!.tabLayoutHome.getTabAt(1)!!.setIcon(R.drawable.ic_edit__1_)
        dataBinding!!.tabLayoutHome.getTabAt(2)!!.setIcon(R.drawable.ic_edit__1_)

        dataBinding!!.tabLayoutHome.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tabglobal = tab

                    //   dataBinding!!.titleofscreen.text = tab.text
                    tab.icon!!.setColorFilter(
                        resources.getColor(R.color.paid_green),
                        PorterDuff.Mode.SRC_IN
                    )

                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    tab.icon!!.setColorFilter(
                        resources.getColor(R.color.dark_grey),
                        PorterDuff.Mode.SRC_IN
                    )
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}

            })

        for (i in 0 until dataBinding!!.tabLayoutHome.getTabCount()) {
            val tab: TabLayout.Tab = dataBinding!!.tabLayoutHome.getTabAt(i)!!
            val relativeLayout = LayoutInflater.from(this)
                .inflate(
                    R.layout.custom_tab_item_design,
                    dataBinding!!.tabLayoutHome,
                    false
                ) as RelativeLayout
            var tvTabText =
                relativeLayout.findViewById<View>(R.id.tab_title) as TextView
            val imageviewicon = relativeLayout.findViewById(R.id.imageof_icon) as ImageView
            imageviewicon.setImageDrawable(tab.icon)
            tvTabText.setText(tab.text)
            tab.customView = relativeLayout
        }
    }

    override fun getResLayout(): Int {
        return R.layout.activity_home
    }

    override fun onClick(view: View) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
//        ViewAnimation.initShowOut(dataBinding!!.)
//        ViewAnimation.initShowOut(dataBinding!!.lytCall)


        val fragment = adapter!!.getItem(position) as Fragment

        if (fragment is PreviewFragment) {

            if (fragment.view != null && !fragment.requireActivity().isFinishing) {
//                fragment?.invoice_id=fragmentPreviewInvoiceForInvoice()?.toString()
//                fragment?.invoice_id = invoice?.invoice_id.toString()
                fragment.getTemplatePreview(viewModel.pdfFile.value)
            }

        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        adapter.addFragment(MainHomefragment(), "Edit")
        adapter.addFragment(PreviewFragment(), "Preview")
        adapter.addFragment(SavedFragment(), "Saved CV")
        viewPager!!.adapter = adapter
    }


}