package com.semixtech.cv_resume_builder.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseFramnet
import com.semixtech.cv_resume_builder.databinding.FragmentPreviewBinding


class PreviewFragment : BaseFramnet<FragmentPreviewBinding>() {

    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {
     }

    override fun getlayout(): Int {
      return R.layout.fragment_preview
    }

}