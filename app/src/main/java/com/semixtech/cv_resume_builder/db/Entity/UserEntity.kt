package com.semixtech.cv_resume_builder.db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userinfo")
data class UserEntity(
    @PrimaryKey val _phone: String,
    @ColumnInfo(name = "firstname") var firstname: String?,
    @ColumnInfo(name = "secondname") var secondname: String?,
    @ColumnInfo(name = "profession") var profession:String?,
    @ColumnInfo(name="city") var city:String?,
    @ColumnInfo(name = "country") var country:String?,
    @ColumnInfo(name = "postalcode")var postalcode: String?,
    @ColumnInfo(name="email") var email:String?,


)