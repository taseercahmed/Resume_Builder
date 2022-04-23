package com.semixtech.cv_resume_builder.db.Dao

import androidx.room.*
import com.semixtech.cv_resume_builder.db.Entity.UserSkillsEntity


@Dao
interface UserskillDao
{

    @Query("SELECT * FROM userskills ORDER BY _id")
    fun getAllskills(): List<UserSkillsEntity>
    @Insert
    fun insertUser(user: UserSkillsEntity?)
    @Delete
    fun deleteUser(user: UserSkillsEntity?)
    @Update
    fun updateUser(user: UserSkillsEntity?)

    @Query("SELECT EXISTS(SELECT * FROM userskills WHERE _id = :id)")
    fun isRowIsExist(id: Int) : Boolean
}