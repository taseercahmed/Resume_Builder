package com.semixtech.cv_resume_builder.db.Dao

import androidx.room.*
import com.semixtech.cv_resume_builder.db.Entity.UserEducationEntity



@Dao
interface UsereducationDao
{

    @Query("SELECT * FROM usereducation ORDER BY _id")
    fun getAlleducation(): List<UserEducationEntity>
    @Insert
    fun insertUser(user: UserEducationEntity?)
    @Delete
    fun deleteUser(user: UserEducationEntity?)
    @Update
    fun updateUser(user: UserEducationEntity?)

    @Query("SELECT EXISTS(SELECT * FROM usereducation WHERE _id = :id)")
    fun isRowIsExist(id: Int) : Boolean
}