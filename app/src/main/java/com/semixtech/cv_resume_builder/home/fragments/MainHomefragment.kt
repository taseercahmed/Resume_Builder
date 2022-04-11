package com.semixtech.cv_resume_builder.home.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.textfield.TextInputEditText
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseFramnet
import com.semixtech.cv_resume_builder.databinding.FragmentMainHomefragmentBinding
import com.semixtech.cv_resume_builder.db.Entity.UserEducationEntity
import com.semixtech.cv_resume_builder.db.Entity.UserEntity
import com.semixtech.cv_resume_builder.db.Entity.UserHistoryEntity
import com.semixtech.cv_resume_builder.home.EducationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.semixtech.cv_resume_builder.kotlinwork.viewmodel.MainViewModel


class MainHomefragment : BaseFramnet<FragmentMainHomefragmentBinding>(),EducationAdapter.RowClickListner  {

    var name1:TextInputEditText?=null
    var name2:TextInputEditText?=null
    var profession:TextInputEditText?=null
    var city1:TextInputEditText?=null
    var country1:TextInputEditText?=null
    var postalcode1:TextInputEditText?=null
    var phone:TextInputEditText?=null
    var email1:TextInputEditText?=null
    var button1:Button?=null
    var button2:Button?=null
    var button3:Button?=null
    var button4:Button?=null
    var SaveButton:Button?=null
    var SaveHistory:Button?=null
    var constraintLayout1:ConstraintLayout?=null
    var constraintLayout2:ConstraintLayout?=null
    var constraintLayout3:ConstraintLayout?=null
    var constraintLayout4:ConstraintLayout?=null

 //  private lateinit var userviewModel: MainViewModel
    private val userviewModel: MainViewModel by viewModel()
    var job:TextInputEditText?=null
    var Employee:TextInputEditText?=null
    var city:TextInputEditText?=null
    var country:TextInputEditText?=null
    var startd:TextInputEditText?=null
    var endd:TextInputEditText?=null
    private lateinit var linearLayoutManager: LinearLayoutManager
    var educationAdapter: EducationAdapter?=null
    var recyclerView:RecyclerView?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





    }
    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {
    }
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())

       dataBinding!!.educationrecycler!!.apply {

            educationAdapter= EducationAdapter(this@MainHomefragment)
        //    adapter=educationAdapter
//            val divider=DividerItemDecoration(context,VERTICAL)
//            addItemDecoration(divider)
        }
        //Without ViewModelFactory
      //  userviewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        userviewModel.getUsereducationObservers().observe(viewLifecycleOwner, Observer
        {
            educationAdapter!!.setListData(ArrayList(it))
            educationAdapter!!.notifyDataSetChanged()
        })
        constraintLayout1=view.findViewById(R.id.headinglayout)
        constraintLayout2=view.findViewById(R.id.workhistorylayout)
        constraintLayout3=view.findViewById(R.id.educationlayout)
//        constraintLayout4 =view.findViewById(R.id.educationrecycler)
        dataBinding!!.workhistorytab!!.setEnabled(false)
        dataBinding!!.educationtab!!.setEnabled(false)
        dataBinding!!.experiencetab!!.setEnabled(false)
        dataBinding!!.headingtab!!.setOnClickListener(View.OnClickListener {
            constraintLayout1!!.setVisibility(View.VISIBLE)
            constraintLayout2!!.setVisibility(View.GONE)
            constraintLayout3!!.setVisibility(View.GONE)
        })

        dataBinding!!.workhistorytab!!.setOnClickListener(View.OnClickListener {
            constraintLayout1!!.setVisibility(View.GONE)
            constraintLayout2!!.setVisibility(View.VISIBLE)
            constraintLayout3!!.setVisibility(View.GONE)
        })
        dataBinding!!.educationtab!!.setOnClickListener(View.OnClickListener {
            constraintLayout1!!.setVisibility(View.GONE)
            constraintLayout2!!.setVisibility(View.GONE)
            constraintLayout3!!.setVisibility(View.VISIBLE)
        })

        dataBinding!!.headinglayout.button1!!.setOnClickListener(View.OnClickListener {
            addData()

            constraintLayout1!!.setVisibility(View.GONE)
            constraintLayout2!!.setVisibility(View.VISIBLE)

            dataBinding!!.workhistorytab!!.setEnabled(true)
            dataBinding!!.workhistorytab.setTextColor(R.color.black_text_color)
            constraintLayout3!!.setVisibility(View.GONE)

        })
        dataBinding!!.workhistorylayout.historybtn.setOnClickListener(View.OnClickListener {
            addHistory()
            constraintLayout1!!.setVisibility(View.GONE)
            constraintLayout2!!.setVisibility(View.GONE)
            constraintLayout3!!.setVisibility(View.VISIBLE)
            dataBinding!!.educationrecycler.rcyclerview.setVisibility(View.GONE)
            dataBinding!!.educationtab!!.setEnabled(false)
            dataBinding!!.educationtab.setTextColor(R.color.black_text_color)
        })

        dataBinding!!.educationlayout.educationbtn.setOnClickListener(View.OnClickListener {
            addEducation()
            constraintLayout1!!.setVisibility(View.GONE)
            constraintLayout2!!.setVisibility(View.GONE)
            constraintLayout3!!.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.rcyclerview.setVisibility(View.VISIBLE)
            dataBinding!!.experiencetab!!.setEnabled(false)
            dataBinding!!.experiencetab.setTextColor(R.color.black_text_color)
        })
    }


    //    fun heading(view: View)
//    {
//
//
//        name2=view.findViewById(R.id.name2)
//        profession=view.findViewById(R.id.profession1)
//        city1=view.findViewById(R.id.city1)
//        country1=view.findViewById(R.id.country1)
//        postalcode1=view.findViewById(R.id.postalcode1)
//        phone=view.findViewById(R.id.phone1)
//        email1=view.findViewById(R.id.emailid)
////        button1=view.findViewById(R.id.headingtab)
////        SaveButton=view.findViewById(R.id.button1)
////        button2=view.findViewById(R.id.workhistorytab)
////        button3=view.findViewById(R.id.educationtab)
////        button4=view.findViewById(R.id.experiencetab)
//    }
//    fun history(view: View)
//    {
//        job=view.findViewById(R.id.jobid)
//        Employee=view.findViewById(R.id.employeeid)
//        city=view.findViewById(R.id.muncipiltyid)
//        country=view.findViewById(R.id.Countryid)
//        startd=view.findViewById(R.id.StartDateid)
//        endd=view.findViewById(R.id.EndDateid)
//        SaveHistory=view.findViewById(R.id.historybtn)
//    }
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
        userviewModel.insertUserInfo(user,requireContext())

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
        userviewModel.insertUserHistory(user,requireContext())

    }
    fun addEducation()
    {
        val SchoolName=dataBinding!!.educationlayout.SchoolName!!.text.toString()
        val SchoolLocation=dataBinding!!.educationlayout.SchoolLocation!!.text.toString()
        val Degree=dataBinding!!.educationlayout.Degree!!.text.toString()
        val FieldofStudy=dataBinding!!.educationlayout.FieldofStudy!!.text.toString()
        val StartDate=dataBinding!!.educationlayout.GraduationStartDate!!.text.toString()
        val EndDate=dataBinding!!.educationlayout.GraduationEndDate!!.text.toString()
         val CurrentlyWork=dataBinding!!.educationlayout.checkedTextView.text.toString()
        if( SchoolName.isNullOrEmpty()){
            dataBinding!!.headinglayout.firstname!!.setError("FirstName is Required")
            return
        }
        val user= UserEducationEntity(0,SchoolName,SchoolLocation,Degree,FieldofStudy,StartDate,EndDate,CurrentlyWork)
        userviewModel.insertUserEducation(user,requireContext())

    }

    override fun getlayout(): Int {
         return R.layout.fragment_main_homefragment
    }

    override fun onDeleteUserClickListner(user: UserEducationEntity) {
        context?.let {
            userviewModel.deleteUserEducation(user, it)
        }
    }

    override fun onItemClickListner(user: UserEducationEntity) {
        TODO("Not yet implemented")
    }




}