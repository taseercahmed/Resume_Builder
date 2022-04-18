package com.semixtech.cv_resume_builder.home.fragments


import android.os.Bundle
import android.view.LayoutInflater
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.base.BaseFramnet
import com.semixtech.cv_resume_builder.databinding.EducationBinding


class WorkhistoryFragment :BaseFramnet<EducationBinding>() {
//    private val userviewModel: MainViewModel by viewModel()
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    var recyclerAdapter: RecyclerViewAdapter1?=null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View?
//    {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.education, container, false)
//    }
//

    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {


//        dataBinding!!.rcyclerview!!.apply {
//            linearLayoutManager = LinearLayoutManager(requireContext())
//            recyclerAdapter= RecyclerViewAdapter1(this@WorkhistoryFragment)
//                adapter=recyclerAdapter
////            val divider= DividerItemDecoration(context,VERTICAL)
////            addItemDecoration(divider)
//            Log.i("TAG","hjhj"+recyclerAdapter)
//        }
//        //Without ViewModelFactory
//        //  userviewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        userviewModel.getUsereducationObservers().observe(this, Observer
//        {
//            recyclerAdapter!!.setListData(ArrayList(it))
//            recyclerAdapter!!.notifyDataSetChanged()
//        })
//        dataBinding!!.savebtn.setOnClickListener(View.OnClickListener {
//            val Degree=dataBinding!!.dname.text.toString()
//            val StartDate=dataBinding!!.date.text.toString()
//            val EndDate=dataBinding!!.enddate.text.toString()
//            val SchoolLocation=dataBinding!!.address.text.toString()
//            val FieldofStudy=dataBinding!!.studyfield.text.toString()
//            val SchoolName=dataBinding!!.scln.text.toString()
//
//            if(dataBinding!!.savebtn.text.equals("Save")) {
//                val user=UserEducationEntity(0,SchoolName,SchoolLocation,Degree,FieldofStudy,StartDate,EndDate,"")
//               Log.i("TAG","Isdatainserted?"+user)
//                userviewModel.insertUserEducation(user,requireContext())
//                Snackbar.make(requireView(), "Your Data is inserted ", Snackbar.LENGTH_SHORT).show()
//                Log.i("TAG","datainserted"+user)
//            } else {
//                val user = UserEducationEntity(dataBinding!!.dname.getTag(dataBinding!!.dname.id).toString().toInt(), SchoolName,SchoolLocation,Degree,FieldofStudy,StartDate,EndDate,"")
//                userviewModel.updateUserEducation(user,requireContext())
//                Snackbar.make(requireView(), "Your Data is Updated", Snackbar.LENGTH_SHORT).show()
//                dataBinding!!.savebtn.setText("Save")
//            }
//            dataBinding!!.dname.setText("")
//            dataBinding!!.date.setText("")
//            dataBinding!!.enddate.setText("")
//            dataBinding!!.address.setText("")
//            dataBinding!!.studyfield.setText("")
//            dataBinding!!.scln.setText("")
//
//        })

    }
//    override fun onDeleteUserClickListner(user: UserEducationEntity) {
//      //  userviewModel.deleteUserEducation(user,requireContext())
//    }

//    override fun onItemClickListner(user: UserEducationEntity)
//    {
//        dataBinding!!.dname.setText(user.Degree)
//        dataBinding!!.date.setText(user.StartDate)
//        dataBinding!!.enddate.setText(user.EndDate)
//        dataBinding!!.address.setText(user.SchoolLocation)
//        dataBinding!!.studyfield.setText(user.FieldofStudy)
//        dataBinding!!.scln.setText(user.SchoolName)
//        dataBinding!!.dname.setTag( dataBinding!!.dname.id, user._id)
//        dataBinding!!.savebtn.setText("Update")
//
//    }

    override fun getlayout(): Int {
        return R.layout.education
    }

}