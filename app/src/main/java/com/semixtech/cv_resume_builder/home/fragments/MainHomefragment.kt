package com.semixtech.cv_resume_builder.home.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
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
import com.semixtech.cv_resume_builder.home.adapter.WorkHistoryAdapter
import java.util.*
import kotlin.collections.ArrayList


class MainHomefragment : BaseFramnet<FragmentMainHomefragmentBinding>(), RecyclerViewAdapter.RowClickListener,WorkHistoryAdapter.RowClickListner
{
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var recyclerViewAdapter1: WorkHistoryAdapter
    lateinit var viewModel: MainViewModel
    @SuppressLint("ResourceAsColor")
    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {


    }


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this!!)[MainViewModel::class.java]
        dataBinding!!.educationrecycler.recyclerView!!.apply {

            layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAdapter = RecyclerViewAdapter(this@MainHomefragment)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(requireContext(), StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)

        }
        dataBinding!!.historyrecycler.recyclerView1!!.apply {
            layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAdapter1 =WorkHistoryAdapter(this@MainHomefragment)
            adapter = recyclerViewAdapter1
            val divider = DividerItemDecoration(requireContext(), StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
        dataBinding!!.workhistorylayout.StartDateid.setOnClickListener(View.OnClickListener {
            val c= Calendar.getInstance()
            val year=c.get(Calendar.YEAR)
            val month=c.get(Calendar.MONTH)
            val day=c.get(Calendar.DAY_OF_MONTH)
            val Startd=DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dataBinding!!.workhistorylayout.StartDateid!!.setText(""+dayOfMonth+"-"+month+"-"+year)

            },year,month,day)
            Startd.show()
        })
        dataBinding!!.workhistorylayout.EndDateid.setOnClickListener(View.OnClickListener {
            val c= Calendar.getInstance()
            val year=c.get(Calendar.YEAR)
            val month=c.get(Calendar.MONTH)
            val day=c.get(Calendar.DAY_OF_MONTH)
            val Startd=DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dataBinding!!.workhistorylayout.EndDateid!!.setText(""+dayOfMonth+"-"+month+"-"+year)

            },year,month,day)
            Startd.show()
        })

             dataBinding!!.educationlayout.GraduationStartDate.setOnClickListener {
                 val c= Calendar.getInstance()
                 val year=c.get(Calendar.YEAR)
                 val month=c.get(Calendar.MONTH)
                 val day=c.get(Calendar.DAY_OF_MONTH)
           val Startd=DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
           dataBinding!!.educationlayout.GraduationStartDate!!.setText(""+dayOfMonth+"-"+month+"-"+year)

           },year,month,day)
                 Startd.show()
             }

        dataBinding!!.educationlayout.GraduationEndDate.setOnClickListener {
            val c= Calendar.getInstance()
            val year=c.get(Calendar.YEAR)
            val month=c.get(Calendar.MONTH)
            val day=c.get(Calendar.DAY_OF_MONTH)
            val Startd=DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dataBinding!!.educationlayout.GraduationEndDate!!.setText(""+dayOfMonth+"-"+month+"-"+year)

            },year,month,day)
            Startd.show()
        }
        viewModel.getUsereducationObservers().observe(requireActivity(),
            Observer {
             if(it.size>0){
                 recyclerViewAdapter.setListData(ArrayList(it))
                 recyclerViewAdapter.notifyDataSetChanged()
                 var user=it.get(0)
                 dataBinding!!.educationlayout.Degree.setText(user.Degree)
                 dataBinding!!.educationlayout.schoolname.setText(user.SchoolName)
                 dataBinding!!.educationlayout.GraduationStartDate.setText(user.StartDate)
                 dataBinding!!.educationlayout.GraduationEndDate.setText(user.EndDate)

             }
        })

        viewModel.getUsersObservers().observe(requireActivity(),{
            if(it.size>0){
                var user=it.get(0)
                dataBinding!!.headinglayout.firstname.setText(user.firstname)
                dataBinding!!.headinglayout.name2.setText(user.secondname)
                dataBinding!!.headinglayout.profession1.setText(user.profession)
                dataBinding!!.headinglayout.city1.setText(user.city)
                dataBinding!!.headinglayout.country1.setText(user.country)
                dataBinding!!.headinglayout.phone1.setText(user._phone)
                dataBinding!!.headinglayout.emailid.setText(user.email)
            }
        })
        viewModel.getUserhistoryObservers().observe(requireActivity(),
            Observer {
             if(it.size>0){
                 recyclerViewAdapter1.setListData(ArrayList(it))
                 recyclerViewAdapter1.notifyDataSetChanged()
                 var user=it.get(0)
                 dataBinding!!.workhistorylayout.jobid.setText(user.jobtitle)
                 dataBinding!!.workhistorylayout.employeeid.setText(user.Employer)
                 dataBinding!!.workhistorylayout.muncipiltyid.setText(user.City)
                 dataBinding!!.workhistorylayout.Countryid.setText(user.Country)
                 dataBinding!!.workhistorylayout.StartDateid.setText(user.startdate)
                 dataBinding!!.workhistorylayout.EndDateid.setText(user.endDate)
                 dataBinding!!.workhistorylayout.checkedTextView.setText(user.currentlywork)

             }
        })

        dataBinding!!.workhistorytab!!.setEnabled(false)
        dataBinding!!.educationtab!!.setEnabled(false)
        dataBinding!!.skillstab!!.setEnabled(false)
        dataBinding!!.headingtab!!.setOnClickListener(View.OnClickListener {
            dataBinding!!.headinglayout!!.heading.setVisibility(View.VISIBLE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)
        })

        dataBinding!!.workhistorytab!!.setOnClickListener(View.OnClickListener {
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)
        })
        dataBinding!!.educationtab!!.setOnClickListener(View.OnClickListener {
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.VISIBLE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)
        })

        dataBinding!!.headinglayout.button1!!.setOnClickListener(View.OnClickListener {
            addData()
        })
        dataBinding!!.workhistorylayout.historybtn.setOnClickListener(View.OnClickListener {
            addHistory()

        })
        dataBinding!!.workhistorylayout.bckhistorybtn.setOnClickListener(View.OnClickListener {

            dataBinding!!.headinglayout!!.heading.setVisibility(View.VISIBLE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.workhistorytab!!.setEnabled(false)

        })

        dataBinding!!.educationlayout.educationbtn.setOnClickListener(View.OnClickListener {
            addEducation()


        })
        dataBinding!!.educationlayout.bckeducationbtn.setOnClickListener(View.OnClickListener {

            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.historyrecycler.historyrec.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)


        })
        dataBinding!!.educationrecycler.floatingActionButton.setOnClickListener(View.OnClickListener
        {
            dataBinding!!.educationlayout.schoolname.setText("")
            dataBinding!!.educationlayout.Degree.setText("")
            dataBinding!!.educationlayout.GraduationStartDate.setText("")
            dataBinding!!.educationlayout.GraduationEndDate.setText("")
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.VISIBLE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)

        })
        dataBinding!!.historyrecycler.floatingBackButton.setOnClickListener(View.OnClickListener
        {
            dataBinding!!.workhistorylayout.jobid.setText("")
            dataBinding!!.workhistorylayout.employeeid.setText("")
            dataBinding!!.workhistorylayout.muncipiltyid.setText("")
            dataBinding!!.workhistorylayout.Countryid.setText("")
            dataBinding!!.workhistorylayout.StartDateid.setText("")
            dataBinding!!.workhistorylayout.EndDateid.setText("")
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
             dataBinding!!.historyrecycler.historyrec.setVisibility(View.GONE)

        })
        dataBinding!!.historyrecycler.nexthistorybutton.setOnClickListener(View.OnClickListener {
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.historyrecycler.historyrec.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.VISIBLE)

            dataBinding!!.educationtab!!.setEnabled(false)
            dataBinding!!.educationtab.setTextColor(R.color.black_text_color)
        })
        dataBinding!!.historyrecycler.bckhistorybutton.setOnClickListener(View.OnClickListener {


            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.historyrecycler.historyrec.setVisibility(View.GONE)
            dataBinding!!.educationtab!!.setEnabled(false)

            dataBinding!!.educationtab.setTextColor(R.color.light_grey)
        })
    }
    override fun getlayout(): Int {
        return R.layout.fragment_main_homefragment
    }

    @SuppressLint("ResourceAsColor")
    fun addData(){
        var firstname=dataBinding!!.headinglayout.firstname!!.text.toString()
        var secondname=dataBinding!!.headinglayout.name2!!.text.toString()
        var profession1=dataBinding!!.headinglayout.profession1!!.text.toString()
        var city=dataBinding!!.headinglayout.city1!!.text.toString()
        var country=dataBinding!!.headinglayout.country1!!.text.toString()
        var postalcode=dataBinding!!.headinglayout.postalcode1!!.text.toString()
        var phone1=dataBinding!!.headinglayout.phone1!!.text.toString()
        var email=dataBinding!!.headinglayout.emailid!!.text.toString()

        if(firstname.isNullOrEmpty() || firstname==""){
            dataBinding!!.headinglayout.firstname!!.setError("Required")
            return
        }

        if(secondname.isNullOrEmpty() || firstname==""){
            dataBinding!!.headinglayout.name2!!.setError("Required")
            return
        }
        if(profession1.isNullOrEmpty() || profession1==""){
            dataBinding!!.headinglayout.profession1!!.setError("Required")
            return
        }
        if(city.isNullOrEmpty() || city==""){
            dataBinding!!.headinglayout.city1!!.setError("Required")
            return
        }
        if(country.isNullOrEmpty() || country==""){
            dataBinding!!.headinglayout.country1!!.setError("Required")
            return
        }
//        if(postalcode.isNullOrEmpty() || postalcode==""){
//            dataBinding!!.headinglayout.postalcode1!!.setError("Required")
//            return
//        }
        if(phone1.isNullOrEmpty() || phone1==""){
            dataBinding!!.headinglayout.phone1!!.setError("Required")
            return
        }
        if(email.isNullOrEmpty() || email==""){
            dataBinding!!.headinglayout.emailid!!.setError("Required")
            return
        }
        Log.i("TAG", "addData:343434 ")
        if(viewModel.isExistUsr(requireActivity(),phone1)){
            val user= UserEntity(phone1,firstname,secondname,profession1,city,country,postalcode,email)
            viewModel.updateUserInfo(user,requireContext())
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.workhistorytab!!.setEnabled(true)
            dataBinding!!.workhistorytab.setTextColor(R.color.black_text_color)
        }else{
            val user= UserEntity(phone1,firstname,secondname,profession1,city,country,postalcode,email)
            viewModel.insertUserInfo(user,requireContext())
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.workhistorytab!!.setEnabled(true)
            dataBinding!!.workhistorytab.setTextColor(R.color.black_text_color)
        }







    }
    @SuppressLint("ResourceAsColor")
    fun addHistory()
    {
        val jobtitle=dataBinding!!.workhistorylayout.jobid!!.text.toString()
        val Employer=dataBinding!!.workhistorylayout.employeeid!!.text.toString()
        val City=dataBinding!!.workhistorylayout.muncipiltyid!!.text.toString()
        val Country=dataBinding!!.workhistorylayout.Countryid!!.text.toString()
        val startdate=dataBinding!!.workhistorylayout.StartDateid!!.text.toString()
        val enddate=dataBinding!!.workhistorylayout.EndDateid!!.text.toString()
        if( jobtitle.isNullOrEmpty() || jobtitle == ""){
            dataBinding!!.workhistorylayout.jobid!!.setError("Required")
            return
        }
        if( Employer.isNullOrEmpty() || Employer == ""){
            dataBinding!!.workhistorylayout.employeeid!!.setError("Required")
            return
        }
        if( City.isNullOrEmpty() || City == ""){
            dataBinding!!.workhistorylayout.muncipiltyid!!.setError("Required")
            return
        }
        if( Country.isNullOrEmpty() || Country == ""){
            dataBinding!!.workhistorylayout.Countryid!!.setError("Required")
            return
        }
        if( startdate.isNullOrEmpty() || startdate == ""){
            dataBinding!!.workhistorylayout.StartDateid!!.setError("Required")
            return
        }
        if( enddate.isNullOrEmpty() || enddate == ""){
            dataBinding!!.workhistorylayout.EndDateid!!.setError("Required")
            return
        }
        if (viewModel.isExistUsrHistory(requireActivity(),0))
        {
            if(dataBinding!!.workhistorylayout.historybtn.text.equals("Next"))
            {
                val user= UserHistoryEntity(0,jobtitle,Employer,City,Country,startdate,enddate,"")
                viewModel.updateUserHistory(user,requireContext())

            } else {
                val user= UserHistoryEntity(  dataBinding!!.workhistorylayout.jobid.getTag(dataBinding!!.workhistorylayout.jobid.id).toString().toInt()
                    ,jobtitle,Employer,City,Country,startdate,enddate,"")
                viewModel.updateUserHistory(user,requireContext())
                dataBinding!!.workhistorylayout.historybtn.setText("Next")
            }
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)
            dataBinding!!.historyrecycler.historyrec.setVisibility(View.VISIBLE)

        }else
        {
            if(dataBinding!!.workhistorylayout.historybtn.text.equals("Next"))
            {

                val user= UserHistoryEntity(0,jobtitle,Employer,City,Country,startdate,enddate,"")
                viewModel.insertUserHistory(user,requireContext())

            } else {
                val user= UserHistoryEntity(  dataBinding!!.workhistorylayout.jobid.getTag(dataBinding!!.workhistorylayout.jobid.id).toString().toInt()
                    ,jobtitle,Employer,City,Country,startdate,enddate,"")
                viewModel.updateUserHistory(user,requireContext())
                dataBinding!!.workhistorylayout.historybtn.setText("Next")
            }
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)
            dataBinding!!.historyrecycler.historyrec.setVisibility(View.VISIBLE)
        }



    }

    fun addEducation()
    {

        val SchoolName=dataBinding!!.educationlayout.schoolname!!.text.toString()
        val Degree=dataBinding!!.educationlayout.Degree!!.text.toString()
        val StartDate=dataBinding!!.educationlayout.GraduationStartDate!!.text.toString()
        val EndDate=dataBinding!!.educationlayout.GraduationEndDate!!.text.toString()


        val CurrentlyWork=dataBinding!!.educationlayout.checkedTextView.text.toString()
        if( SchoolName.isNullOrEmpty()){
            dataBinding!!.educationlayout.schoolname!!.setError("Required")
            return
        }

        if( Degree.isNullOrEmpty() || Degree==""){
            dataBinding!!.educationlayout.Degree!!.setError("Required")
            return
        }
        if(StartDate.isNullOrEmpty() || StartDate == ""){
            dataBinding!!.educationlayout.GraduationStartDate!!.setError("Required")
            return
        }
        if( EndDate.isNullOrEmpty()  || EndDate==""){
            dataBinding!!.educationlayout.GraduationEndDate!!.setError("Required")
            return
        }
        if( CurrentlyWork.isNullOrEmpty()  || CurrentlyWork==""){
            dataBinding!!.educationlayout.checkedTextView!!.setError("Required")
            return
        }
        if(viewModel.isExistUsrEducation(requireActivity(),0))
        {
            if(dataBinding!!.educationlayout.educationbtn.text.equals("Next"))
            {
                val user= UserEducationEntity(0,SchoolName,Degree,StartDate,EndDate,CurrentlyWork)
                viewModel.updateUserEducation(user,requireContext())

            } else {
                val user= UserEducationEntity(  dataBinding!!.educationlayout.schoolname.getTag(dataBinding!!.educationlayout.schoolname.id).toString().toInt()
                    ,SchoolName,Degree,StartDate,EndDate,CurrentlyWork)
                viewModel.updateUserEducation(user,requireContext())
                dataBinding!!.educationlayout.educationbtn.setText("Next")
            }
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.VISIBLE)

        }
        else
        {
            if(dataBinding!!.educationlayout.educationbtn.text.equals("Next"))
            {
                val user= UserEducationEntity(0,SchoolName,Degree,StartDate,EndDate,CurrentlyWork)
                viewModel.insertUserEducation(user,requireContext())

            } else {
                val user= UserEducationEntity(  dataBinding!!.educationlayout.schoolname.getTag(dataBinding!!.educationlayout.schoolname.id).toString().toInt()
                    ,SchoolName,Degree,StartDate,EndDate,CurrentlyWork)
                viewModel.updateUserEducation(user,requireContext())
                dataBinding!!.educationlayout.educationbtn.setText("Next")
            }
            dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
            dataBinding!!.workhistorylayout.workhistory.setVisibility(View.GONE)
            dataBinding!!.educationlayout.education.setVisibility(View.GONE)
            dataBinding!!.educationrecycler.educationrec.setVisibility(View.VISIBLE)

//            dataBinding!!.educationlayout.schoolname.setText("")
//            dataBinding!!.educationlayout.SchoolLocation.setText("")
//            dataBinding!!.educationlayout.Degree.setText("")
//            dataBinding!!.educationlayout.FieldofStudy.setText("")
//            dataBinding!!.educationlayout.GraduationStartDate.setText("")
//            dataBinding!!.educationlayout.GraduationEndDate.setText("")
        }



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

        dataBinding!!.educationlayout.Degree.setText(user.Degree)

        dataBinding!!.educationlayout.GraduationStartDate.setText(user.StartDate)
        dataBinding!!.educationlayout.GraduationEndDate.setText(user.EndDate)
        dataBinding!!.educationlayout.schoolname.setTag(dataBinding!!.educationlayout.schoolname.id,user._id)

        dataBinding!!.educationlayout.educationbtn.setText("Update")



    }

    override fun onDeleteUserClickListner(user: UserHistoryEntity) {
       viewModel.deleteUserHistory(user,requireContext())
    }

    override fun onEditClickListner(user: UserHistoryEntity) {
        dataBinding!!.headinglayout!!.heading.setVisibility(View.GONE)
        dataBinding!!.workhistorylayout.workhistory.setVisibility(View.VISIBLE)
        dataBinding!!.educationlayout.education.setVisibility(View.GONE)
        dataBinding!!.educationrecycler.educationrec.setVisibility(View.GONE)
        dataBinding!!.historyrecycler.historyrec.setVisibility(View.GONE)

        dataBinding!!.workhistorylayout.jobid.setText(user.jobtitle)
        dataBinding!!.workhistorylayout.employeeid.setText(user.Employer)
        dataBinding!!.workhistorylayout.muncipiltyid.setText(user.City)
        dataBinding!!.workhistorylayout.Countryid.setText(user.Country)
        dataBinding!!.workhistorylayout.StartDateid.setText(user.startdate)
        dataBinding!!.workhistorylayout.EndDateid.setText(user.endDate)

        dataBinding!!.workhistorylayout.jobid.setTag(dataBinding!!.workhistorylayout.jobid.id,user._id)

        dataBinding!!.workhistorylayout.historybtn.setText("Update")
    }
}