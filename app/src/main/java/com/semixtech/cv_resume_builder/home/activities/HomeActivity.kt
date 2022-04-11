package com.semixtech.cv_resume_builder.home.activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseActivity
import com.semixtech.cv_resume_builder.databinding.ActivityHomeBinding
import com.semixtech.cv_resume_builder.helper.ClickHandler
import com.semixtech.cv_resume_builder.helper.SectionsPagerAdapter
import com.semixtech.cv_resume_builder.home.fragments.MainHomefragment
import com.semixtech.cv_resume_builder.home.fragments.WorkhistoryFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(),ClickHandler,ViewPager.OnPageChangeListener {

    lateinit var adapter: SectionsPagerAdapter
    var tabglobal: TabLayout.Tab? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SectionsPagerAdapter(supportFragmentManager)
        setupViewPager(dataBinding!!.viewPager)
        initSetting()
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
        adapter.addFragment(WorkhistoryFragment(), "Preview")
        adapter.addFragment(MainHomefragment(), "Saved CV")
        viewPager!!.adapter = adapter
    }
}