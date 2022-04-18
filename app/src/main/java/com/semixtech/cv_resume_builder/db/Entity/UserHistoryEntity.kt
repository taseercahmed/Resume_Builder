package com.semixtech.cv_resume_builder.db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userhistory")
data class UserHistoryEntity(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="_id") val _id: Int=0,
    @ColumnInfo(name = "jobtitle")
    var jobtitle: String?,
    @ColumnInfo(name = "Employer")
    var Employer: String?,
    @ColumnInfo(name="City")
    var City:String?,
    @ColumnInfo(name = "Country")
    var Country:String?,
    @ColumnInfo(name = "startdate")
    var startdate:String?,
    @ColumnInfo(name = "enddate")
    var endDate: String?,
    @ColumnInfo(name = "currentlywork")
    var currentlywork: String?,
    )
