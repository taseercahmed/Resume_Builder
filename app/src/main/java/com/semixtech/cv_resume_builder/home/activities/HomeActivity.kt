package com.semixtech.cv_resume_builder.home.activities

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseActivity
import com.semixtech.cv_resume_builder.databinding.ActivityHomeBinding
import com.semixtech.cv_resume_builder.db.Entity.*
import com.semixtech.cv_resume_builder.helper.ClickHandler
import com.semixtech.cv_resume_builder.helper.PdfConverter
import com.semixtech.cv_resume_builder.helper.SectionsPagerAdapter
import com.semixtech.cv_resume_builder.helper.TemplateDefaultModel
import com.semixtech.cv_resume_builder.home.fragments.MainHomefragment
import com.semixtech.cv_resume_builder.home.fragments.PreviewFragment
import com.semixtech.cv_resume_builder.home.fragments.SavedFragment
import com.semixtech.cv_resume_builder.home.templates.Template1
import com.semixtech.cv_resume_builder.kotlinwork.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class HomeActivity : BaseActivity<ActivityHomeBinding>(),ClickHandler,ViewPager.OnPageChangeListener {

    lateinit var adapter: SectionsPagerAdapter
    var tabglobal: TabLayout.Tab? = null
    lateinit var viewModel: MainViewModel
    private lateinit var htmlFile: File
    var converter: PdfConverter? = null
    var pdfFileGenerationJob: Job? = null
    private var backgroundTaskList: MutableList<Job?> = arrayListOf<Job?>(null,null,null,null,null,null,null,null,null,null,null,null,null)
    var previousJobAnalyser: Job? = null
    var pdfGenerationJobFromWebView: Job? = null
    private var templateDefaultModel: TemplateDefaultModel = TemplateDefaultModel()

    fun invoiceCommunicationEditScreen() {
        getPreview()
    }

    private fun getPreview() {
        if (viewModel.pdfFile.value != null) {
            if (viewModel.pdfFile.value!!.exists()) {
                viewModel.pdfFile.value!!.delete()
            }
        }
        if (this@HomeActivity::htmlFile.isInitialized) {
            if (htmlFile.exists()) {
                htmlFile.delete()
            }
        }
        var user:UserEntity?=null
       if(viewModel.Users.value!!.size>0){
           user=viewModel.Users.value!!.get(0)
       }
        var edu:UserEducationEntity?=null
        if(viewModel.Users.value!!.size>0){
            edu=viewModel.UserEducation.value!!.get(0)
        }
        var history:UserHistoryEntity?=null
        if(viewModel.UsersHistory.value!!.size>0){
            history=viewModel.UsersHistory.value!!.get(0)
        }
        var skill:UserSkillsEntity?=null
        if(viewModel.UserSkills.value!!.size>0){
            skill=viewModel.UserSkills.value!!.get(0)
        }
        var summary:UserSummaryEntity?=null
        if(viewModel.UserSummary.value!!.size>0){
            summary=viewModel.UserSummary.value!!.get(0)
        }

        templateDefaultModel= TemplateDefaultModel(user = user!!,workhistory = history!!,userEducationEntity = edu!!,userSkillsEntity = skill!!,userSummaryEntity = summary!!)
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

        pdfFileGenerationJob = GlobalScope.launch(Dispatchers.Main) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                viewModel.pdfFile.value = File(context!!.filesDir.toString() + "/", "ResumeActual.pdf")
            } else {
                viewModel.pdfFile.value = File(Environment.getExternalStorageDirectory().path.toString() + "/", "ResumeActual.pdf")
            }
        }
        backgroundTaskList[3] = pdfFileGenerationJob
        previousJobAnalyser = GlobalScope.launch(Dispatchers.IO) {
            pdfFileGenerationJob!!.join()
//            Toast.makeText(context!!, "Cancling previous one", Toast.LENGTH_SHORT).show()
            if (pdfGenerationJobFromWebView != null) {
                pdfGenerationJobFromWebView!!.cancel()
            }
            pdfGenerationJobFromWebView = GlobalScope.launch(Dispatchers.Unconfined) {
                suspend {
                    converter!!.convert(htmlFile, viewModel.pdfFile.value!!)
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
                Log.e("TAG", "createPdfFromHtml: PDF generation completed...")

            } else {
                createPdfFromHtml(htmlFile)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun saveHtmlTextToFile(htmlText: String): File {

        htmlFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File(
                context!!.filesDir.toString() + "/", "ResumeActual.html"
            )
        } else {
            File(
                Environment.getExternalStorageDirectory().path.toString() + "/",
                "ResumeActual.html"
            )
        }
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

        return generateTemplate("1")
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