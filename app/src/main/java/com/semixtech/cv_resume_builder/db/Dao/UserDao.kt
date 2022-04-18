package com.semixtech.cv_resume_builder.db.Dao

import androidx.room.*
import com.semixtech.cv_resume_builder.db.Entity.UserEntity


@Dao
interface UserDao
{
    @Query("SELECT * FROM userinfo ORDER BY _phone")
    fun getAllinfo(): List<UserEntity>
    @Insert
    fun insertUser(user: UserEntity?)
    @Delete
    fun deleteUser(user: UserEntity?)
    @Update
    fun updateUser(user: UserEntity?)

    @Query("SELECT EXISTS(SELECT * FROM userinfo WHERE _phone = :id)")
    fun isRowIsExist(id : String) : Boolean


}
