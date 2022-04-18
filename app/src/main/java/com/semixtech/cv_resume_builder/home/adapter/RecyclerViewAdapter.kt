package com.semixtech.cv_resume_builder.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.semixtech.cv_resume_builder.R
import com.semixtech.cv_resume_builder.db.Entity.UserEducationEntity



class RecyclerViewAdapter(val listener: RowClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items  = ArrayList<UserEducationEntity>()

    fun setListData(data: ArrayList<UserEducationEntity>)
    {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context).inflate(R.layout.degreerecordlayout, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(items[position])

    }



    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view) {

        val degreerecord :TextView
        val sdate : TextView
        val edate : TextView
        val sname:TextView
        val editbtn :Button
        val deletebtn:Button
        init
        {
            degreerecord = itemView.findViewById(R.id.degreerecord)
            sdate = itemView.findViewById(R.id.sdate)
            edate=itemView.findViewById(R.id.edate)
            sname = itemView.findViewById(R.id.sname)
//            slocation=itemView.findViewById(R.id.slocation)
//            fieldstudy=itemView.findViewById(R.id.fieldstudy)
            deletebtn = itemView.findViewById(R.id.deletebtn)
            editbtn = itemView.findViewById(R.id.editbtn)


        }


        fun bind(data: UserEducationEntity) {
            degreerecord.text = data.Degree

            sdate.text = data.StartDate


            edate.text = data.EndDate
            sname.text = data.SchoolName
//            slocation.text = data.SchoolLocation
//            fieldstudy.text = data.FieldofStudy

            deletebtn.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
            editbtn.setOnClickListener {
                listener.onEditClickListener(data)
            }
        }
    }

    interface RowClickListener{
        fun onDeleteUserClickListener(user: UserEducationEntity)
        fun onEditClickListener(user: UserEducationEntity)
    }
}