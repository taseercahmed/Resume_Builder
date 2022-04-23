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
import com.semixtech.cv_resume_builder.db.Entity.UserSkillsEntity

class SkillsAdapter(val listner:RowClickListner):RecyclerView.Adapter<SkillsAdapter.MyViewHolder>()
{
    var record1= ArrayList<UserSkillsEntity>()
    fun setListData(data:ArrayList<UserSkillsEntity>)
    {
        this.record1=data
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  inflater= LayoutInflater.from(parent.context).inflate(R.layout.skillsrecordlayout,parent,false)
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
        var deletebutton: Button? = null
        var edittbutton: Button? = null

        init
        {

            textView1 = itemView.findViewById(R.id.skillsrecord)

            deletebutton = itemView.findViewById(R.id.deletebtn)
            edittbutton = itemView.findViewById(R.id.editbtn)


        }

        fun bind(data: UserSkillsEntity)
        {
            textView1.text=data.Skills
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

        fun onDeleteUserClickListner(user:UserSkillsEntity)
        fun onEditClickListner(user:UserSkillsEntity)
    }


}