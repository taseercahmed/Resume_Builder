package com.semixtech.cv_resume_builder.kotlinwork.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.semixtech.cv_resume_builder.db.Entity.UserEducationEntity
import com.semixtech.cv_resume_builder.db.Entity.UserEntity
import com.semixtech.cv_resume_builder.db.RoomAppDb
import com.semixtech.cv_resume_builder.db.Entity.UserHistoryEntity


class MainViewModel(app:Application) : AndroidViewModel(app) {

    lateinit var Users:MutableLiveData<List<UserEntity>>
    lateinit var UsersHistory:MutableLiveData<List<UserHistoryEntity>>
    lateinit var UserEducation:MutableLiveData<List<UserEducationEntity>>

   init {
       Users= MutableLiveData()
       UsersHistory= MutableLiveData()
       UserEducation= MutableLiveData()
       getUserEducation(app)
       getUser(app)
       getUserHistory(app)
   }

    fun getUsersObservers():MutableLiveData<List<UserEntity>>
    {
        return Users
    }

    fun getUser(c: Context)
    {
        val userDao= RoomAppDb.getAppDatabase(c)?.UserDao()
        val list=userDao?.getAllinfo()
        Users.postValue(list)
    }
    fun insertUserInfo(entity: UserEntity,c: Context)
    {
        val userDao=RoomAppDb.getAppDatabase(c)?.UserDao()
        userDao?.insertUser(entity)
        getUser(c)
    }
    fun updateUserInfo(entity: UserEntity,c: Context)
    {
        val userDao=RoomAppDb.getAppDatabase(c)?.UserDao()
        userDao?.updateUser(entity)
        getUser(c)
    }

    fun isExistUsr(c: Context,p:String): Boolean {
        val userDao=RoomAppDb.getAppDatabase(c)?.UserDao()
        return userDao!!.isRowIsExist(p)

    }

    fun getUserhistoryObservers():MutableLiveData<List<UserHistoryEntity>>
    {
        return UsersHistory
    }
    fun getUserHistory(c: Context)
    {
        val userHistoryDao= RoomAppDb.getAppDatabase(c)?.UserHistoryDao()
        val list=userHistoryDao?.getAllhistory()
        UsersHistory.postValue(list)
    }
    fun insertUserHistory(entity: UserHistoryEntity,c: Context)
    {
        val userHistoryEntity=RoomAppDb.getAppDatabase(c)?.UserHistoryDao()
        userHistoryEntity?.insertUser(entity)
        getUserHistory(c)
    }
    fun deleteUserHistory(entity: UserHistoryEntity,c: Context)
    {
        val userHistoryEntity=RoomAppDb.getAppDatabase(c)?.UserHistoryDao()
        userHistoryEntity?.deleteUser(entity)
        getUserHistory(c)
    }

    fun updateUserHistory(entity: UserHistoryEntity,c: Context)
    {
        val userHistoryEntity=RoomAppDb.getAppDatabase(c)?.UserHistoryDao()
        userHistoryEntity?.updateUser(entity)
        getUserHistory(c)
    }
    fun isExistUsrHistory(c: Context, p: Int): Boolean {
        val userHistoryDao=RoomAppDb.getAppDatabase(c)?.UserHistoryDao()
        return userHistoryDao!!.isRowIsExist(p)

    }



    fun getUsereducationObservers():MutableLiveData<List<UserEducationEntity>>
    {
        return UserEducation
    }

    fun getUserEducation(c: Context)
    {
        val usereducationDao= RoomAppDb.getAppDatabase(c)?.UsereducationDao()
        val list=usereducationDao?.getAlleducation()
        UserEducation.postValue(list)
    }
    fun insertUserEducation(entity: UserEducationEntity,c: Context)
    {
        val userEducationDao=RoomAppDb.getAppDatabase(c)?.UsereducationDao()
        userEducationDao?.insertUser(entity)
        getUserEducation(c)
    }

    fun deleteUserEducation(entity: UserEducationEntity,c:Context)
    {
        val userEducationDao=RoomAppDb.getAppDatabase(c)?.UsereducationDao()
        userEducationDao?.deleteUser(entity)
        getUserEducation(c)
    }
    fun updateUserEducation(entity: UserEducationEntity,c:Context)
    {
        val userEducationDao=RoomAppDb.getAppDatabase(c)?.UsereducationDao()
        userEducationDao?.updateUser(entity)
        getUserEducation(c)
    }
    fun isExistUsrEducation(c: Context, p: Int): Boolean {
        val usereducationDao=RoomAppDb.getAppDatabase(c)?.UsereducationDao()
        return usereducationDao!!.isRowIsExist(p)

    }
}