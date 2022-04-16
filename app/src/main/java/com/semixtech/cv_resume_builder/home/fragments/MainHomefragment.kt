package com.semixtech.cv_resume_builder.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseFramnet
import com.semixtech.cv_resume_builder.databinding.FragmentMainHomefragmentBinding
import com.semixtech.cv_resume_builder.db.Entity.UserEducationEntity
import com.semixtech.cv_resume_builder.db.Entity.UserEntity
import com.semixtech.cv_resume_builder.db.Entity.UserHistoryEntity


import com.semixtech.cv_resume_builder.kotlinwork.viewmodel.MainViewModel
import com.semixtech.cv_resume_builder.home.adapter.RecyclerViewAdapter


class MainHomefragment : BaseFramnet<FragmentMainHomefragmentBinding>(), RecyclerViewAdapter.RowClickListener
{
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainViewModel
    @SuppressLint("ResourceAsColor")
    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {

        dataBinding!!.educationrecycler.recyclerView!!.apply {

            layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAdapter = RecyclerViewAdapter(this@MainHomefragment)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(requireContext(), StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)

        }
        viewModel = ViewModelProviders.of(this!!)[MainViewModel::class.java]
        viewModel.getUsereducationObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        dataBinding!!.workhistorytab!!.setEnabled(false)
        dataBinding!!.educationtab!!.setEnabled(false)
        dataBinding!!.experiencetab!!.setEnabled(false)
        dataBinding!!.headingtab!!.setOnClickListener(View.OnClickListener {
            dataBinding!!.headinglayout!!.heading.setVisibility(View.VISIBLE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
        })

        dataBinding!!.workhistorytab!!.setOnClickListener(View.OnClickListener {
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
        })
        dataBinding!!.educationtab!!.setOnClickListener(View.OnClickListener {
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.VISIBLE)
        })

        dataBinding!!.headinglayout.button1!!.setOnClickListener(View.OnClickListener {
            addData()

            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.workhistorytab!!.setEnabled(true)
            dataBinding!!.workhistorytab.setTextColor(R.color.black_text_color)


        })
        dataBinding!!.workhistorylayout.historybtn.setOnClickListener(View.OnClickListener {
            addHistory()
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.VISIBLE)

            dataBinding!!.educationtab!!.setEnabled(false)
            dataBinding!!.educationtab.setTextColor(R.color.black_text_color)
        })

        dataBinding!!.educationlayout.educationbtn.setOnClickListener(View.OnClickListener {
            addEducation()
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.VISIBLE)

        })
    }

    override fun getlayout(): Int {
        return R.layout.fragment_main_homefragment
    }

    fun addData(){
        var firstname=dataBinding!!.headinglayout.firstname!!.text.toString()
        var secondname=dataBinding!!.headinglayout.name2!!.text.toString()
        var profession1=dataBinding!!.headinglayout.profession1!!.text.toString()
        var city=dataBinding!!.headinglayout.city1!!.text.toString()
        var country=dataBinding!!.headinglayout.country1!!.text.toString()
        var postalcode=dataBinding!!.headinglayout.postalcode1!!.text.toString()
        var phone1=dataBinding!!.headinglayout.phone1!!.text.toString()
        var email=dataBinding!!.headinglayout.emailid!!.text.toString()
        if(firstname.isNullOrEmpty()){
            dataBinding!!.headinglayout.firstname!!.setError("FirstName is Required")
            return
        }


        val user= UserEntity(phone1,firstname,secondname,profession1,city,country,postalcode,email)
        viewModel.insertUserInfo(user,requireContext())

    }
    fun addHistory()
    {
        val jobtitle=dataBinding!!.workhistorylayout.jobid!!.text.toString()
        val Employer=dataBinding!!.workhistorylayout.employeeid!!.text.toString()
        val City=dataBinding!!.workhistorylayout.muncipiltyid!!.text.toString()
        val Country=dataBinding!!.workhistorylayout.Countryid!!.text.toString()
        val startdate=dataBinding!!.workhistorylayout.StartDateid!!.text.toString()
        val enddate=dataBinding!!.workhistorylayout.EndDateid!!.text.toString()
        if( jobtitle.isNullOrEmpty()){
            dataBinding!!.headinglayout.firstname!!.setError("FirstName is Required")
            return
        }

        val user= UserHistoryEntity(0,jobtitle,Employer,City,Country,startdate,enddate,"")
        viewModel.insertUserHistory(user,requireContext())

    }

    fun addEducation()
    {
        val SchoolName=dataBinding!!.educationlayout.schoolname!!.text.toString()
        val SchoolLocation=dataBinding!!.educationlayout.SchoolLocation!!.text.toString()
        val Degree=dataBinding!!.educationlayout.Degree!!.text.toString()
        val FieldofStudy=dataBinding!!.educationlayout.FieldofStudy!!.text.toString()
        val StartDate=dataBinding!!.educationlayout.GraduationStartDate!!.text.toString()
        val EndDate=dataBinding!!.educationlayout.GraduationEndDate!!.text.toString()
        val CurrentlyWork=dataBinding!!.educationlayout.checkedTextView.text.toString()
        if( SchoolName.isNullOrEmpty()){
            dataBinding!!.educationlayout.schoolname!!.setError("FirstName is Required")
            return
        }

        if(dataBinding!!.educationlayout.educationbtn.text.equals("Next"))
        {
            val user= UserEducationEntity(0,SchoolName,SchoolLocation,Degree,FieldofStudy,StartDate,EndDate,CurrentlyWork)
            viewModel.insertUserEducation(user,requireContext())

        } else {
            val user= UserEducationEntity(  dataBinding!!.educationlayout.schoolname.getTag(dataBinding!!.educationlayout.schoolname.id).toString().toInt()
                ,SchoolName,SchoolLocation,Degree,FieldofStudy,StartDate,EndDate,CurrentlyWork)
            viewModel.updateUserEducation(user,requireContext())
            dataBinding!!.educationlayout.educationbtn.setText("Next")
        }

        dataBinding!!.educationlayout.schoolname.setText("")
        dataBinding!!.educationlayout.SchoolLocation.setText("")
        dataBinding!!.educationlayout.Degree.setText("")
        dataBinding!!.educationlayout.FieldofStudy.setText("")
        dataBinding!!.educationlayout.GraduationStartDate.setText("")
        dataBinding!!.educationlayout.GraduationEndDate.setText("")

    }

    override fun onDeleteUserClickListener(user: UserEducationEntity) {
        viewModel.deleteUserEducation(user,requireContext())
    }

    override fun onEditClickListener(user: UserEducationEntity)
    {
        dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
        dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
        dataBinding!!.educationlayout.education.setVisibility(View.VISIBLE)
        dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)

        dataBinding!!.educationlayout.schoolname.setText(user.SchoolName)
        dataBinding!!.educationlayout.SchoolLocation.setText(user.SchoolLocation)
        dataBinding!!.educationlayout.Degree.setText(user.Degree)
        dataBinding!!.educationlayout.FieldofStudy.setText(user.FieldofStudy)
        dataBinding!!.educationlayout.GraduationStartDate.setText(user.StartDate)
        dataBinding!!.educationlayout.GraduationEndDate.setText(user.EndDate)
        dataBinding!!.educationlayout.schoolname.setTag(dataBinding!!.educationlayout.schoolname.id,user._id)

        dataBinding!!.educationlayout.educationbtn.setText("Update")



    }
   }