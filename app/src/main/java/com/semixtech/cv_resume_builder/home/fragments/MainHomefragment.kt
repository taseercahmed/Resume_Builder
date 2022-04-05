package com.semixtech.cv_resume_builder.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.semixtech.cv_resume_builder.R


class MainHomefragment : Fragment()  {

    var tabLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main_homefragment, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout_home)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentcontainer, HeadingFragment()).commit()
        }
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fragmentcontainer, HeadingFragment()).commit()

                    }
                    1 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fragmentcontainer, HeadingFragment()).commit()
                    }
                    2 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fragmentcontainer, HeadingFragment()).commit()
                    }
                    3 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fragmentcontainer, HeadingFragment()).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return view
    }



}