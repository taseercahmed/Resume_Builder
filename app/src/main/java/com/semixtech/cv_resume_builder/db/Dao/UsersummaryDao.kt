package com.semixtech.cv_resume_builder.db.Dao

import androidx.room.*
import com.semixtech.cv_resume_builder.db.Entity.UserSkillsEntity
import com.semixtech.cv_resume_builder.db.Entity.UserSummaryEntity


@Dao
interface UsersummaryDao
{

    @Query("SELECT * FROM usersummary ORDER BY _id")
    fun getAllsummary(): List<UserSummaryEntity>
    @Insert
    fun insertUser(user: UserSummaryEntity?)
    @Delete
    fun deleteUser(user: UserSummaryEntity?)
    @Update
    fun updateUser(user: UserSummaryEntity?)

    @Query("SELECT EXISTS(SELECT * FROM usersummary WHERE _id = :id)")
    fun isRowIsExist(id: Int) : Boolean
}