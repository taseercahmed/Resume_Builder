package com.semixtech.cv_resume_builder.db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userskills")
class UserSkillsEntity
    (

    @PrimaryKey (autoGenerate = true) @ColumnInfo(name="_id") val _id: Int=0,
    @ColumnInfo(name = "Skills") var Skills: String?,

        )
