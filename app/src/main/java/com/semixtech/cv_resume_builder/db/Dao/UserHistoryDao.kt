package com.semixtech.cv_resume_builder.db.Dao

import androidx.room.*
import com.semixtech.cv_resume_builder.db.Entity.UserHistoryEntity

@Dao
interface UserHistoryDao
{
    @Query("SELECT * FROM userhistory ORDER BY _id")
    fun getAllhistory(): List<UserHistoryEntity>
    @Insert
    fun insertUser(user: UserHistoryEntity?)
    @Delete
    fun deleteUser(user: UserHistoryEntity?)
    @Update
    fun updateUser(user: UserHistoryEntity?)

    @Query("SELECT EXISTS(SELECT * FROM userhistory WHERE _id = :id)")
    fun isRowIsExist(id: Int) : Boolean
}