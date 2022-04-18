package com.semixtech.cv_resume_builder.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.db.Entity.UserEducationEntity
import com.semixtech.cv_resume_builder.db.Entity.UserHistoryEntity

class WorkHistoryAdapter(val listner:RowClickListner):RecyclerView.Adapter<WorkHistoryAdapter.MyViewHolder>()
{
    var record1= ArrayList<UserHistoryEntity>()
    fun setListData(data:ArrayList<UserHistoryEntity>)
    {
        this.record1=data
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  inflater= LayoutInflater.from(parent.context).inflate(R.layout.historyrecordlayout,parent,false)
        return MyViewHolder(inflater,listner)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(record1[position])
        Log.i("TAG","Checkdata"+record1[position])
    }

    override fun getItemCount(): Int {
        return record1.size
    }


    class MyViewHolder(view: View,val listner:RowClickListner):RecyclerView.ViewHolder(view)
    {
        var textView1: TextView
        var textView2: TextView
        var textView3: TextView
        var textView4: TextView
        var deletebutton: Button? = null
        var edittbutton: Button? = null

        init
        {
            textView2 = itemView.findViewById(R.id.cname)
            textView1 = itemView.findViewById(R.id.jobtitle)
            textView3=itemView.findViewById(R.id.sdate)
            textView4 = itemView.findViewById(R.id.edate)
            deletebutton = itemView.findViewById(R.id.deletebtn)
            edittbutton = itemView.findViewById(R.id.editbtn)


        }

        fun bind(data: UserHistoryEntity)
        {
            textView1.text=data.jobtitle
            textView2.text=data.Employer
            textView3.text=data.startdate
            textView4.text=data.endDate
//            textView5.text=data.SchoolLocation
//            textView6.text=data.FieldofStudy
            deletebutton!!.setOnClickListener(View.OnClickListener
            {
               listner.onDeleteUserClickListner(data)
            })
            edittbutton!!.setOnClickListener(View.OnClickListener
            {
                listner.onEditClickListner(data)

            })

        }

    }
    interface RowClickListner
    {

        fun onDeleteUserClickListner(user:UserHistoryEntity)
        fun onEditClickListner(user:UserHistoryEntity)
    }


}