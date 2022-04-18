package com.semixtech.cv_resume_builder.db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usereducation")
class UserEducationEntity
    (

    @PrimaryKey (autoGenerate = true) @ColumnInfo(name="_id") val _id: Int=0,
    @ColumnInfo(name = "SchoolName") var SchoolName: String?,
  //  @ColumnInfo(name = "SchoolLocation") var SchoolLocation: String?,
    @ColumnInfo(name = "Degree") var Degree:String?,
  //  @ColumnInfo(name="FieldofStudy") var FieldofStudy:String?,
    @ColumnInfo(name = "StartDate") var StartDate:String?,
    @ColumnInfo(name = "EndDate")var EndDate: String?,
    @ColumnInfo(name="CurrentlyWork") var CurrentlyWork:String?,
        )
