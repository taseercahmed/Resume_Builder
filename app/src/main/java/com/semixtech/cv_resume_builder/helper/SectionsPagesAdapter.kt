package com.semixtech.cv_resume_builder.helper

import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

class SectionsPagerAdapter(manager: FragmentManager) :
    FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    fun onTouchEvent(event: MotionEvent?): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
}