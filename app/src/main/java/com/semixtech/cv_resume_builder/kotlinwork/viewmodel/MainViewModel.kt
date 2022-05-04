package com.semixtech.cv_resume_builder.kotlinwork.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.semixtech.cv_resume_builder.db.Entity.*
import com.semixtech.cv_resume_builder.db.RoomAppDb

import java.io.File


class MainViewModel(app:Application) : AndroidViewModel(app) {

    lateinit var Users:MutableLiveData<List<UserEntity>>
    lateinit var UsersHistory:MutableLiveData<List<UserHistoryEntity>>
    lateinit var UserEducation:MutableLiveData<List<UserEducationEntity>>
    lateinit var UserSkills:MutableLiveData<List<UserSkillsEntity>>
    lateinit var UserSummary:MutableLiveData<List<UserSummaryEntity>>
    var pdfFile = MutableLiveData<File>()
    var pdfStatus = MutableLiveData<String>()

   init {
       Users= MutableLiveData()
       UsersHistory= MutableLiveData()
       UserEducation= MutableLiveData()
       UserSkills= MutableLiveData()
       UserSummary= MutableLiveData()
       getUserEducation(app)
       getUser(app)
       getUserHistory(app)
       getUserSkills(app)

   }

    //These is all about heading means personal info
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


    //These is all about Work History of User

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

    //These is all about User Education
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

    //Now its time of User Skills

    fun getUserSkillsObservers():MutableLiveData<List<UserSkillsEntity>>
    {
        return UserSkills
    }
    fun getUserSkills(c:Context)
    {
        val userskillsDao = RoomAppDb.getAppDatabase(c)?.UserskillDao()
        val list=userskillsDao?.getAllskills()
        UserSkills.postValue(list)
    }
    fun insertUserSkills(entity: UserSkillsEntity,c:Context)
    {
        val userSkillsDao=RoomAppDb.getAppDatabase(c)?.UserskillDao()
        userSkillsDao?.insertUser(entity)
        getUserSkills(c)
    }
    fun deleteUserSkills(entity: UserSkillsEntity,c:Context)
    {
        val userSkillsDao=RoomAppDb.getAppDatabase(c)?.UserskillDao()
        userSkillsDao?.deleteUser(entity)
        getUserSkills(c)
    }
    fun UpdateUserSkills(entity: UserSkillsEntity,c:Context)
    {
        val userSkillsDao=RoomAppDb.getAppDatabase(c)?.UserskillDao()
        userSkillsDao?.updateUser(entity)
        getUserSkills(c)
    }
    fun isExistUsrSkills(c: Context, p: Int): Boolean {
        val userskillsdao=RoomAppDb.getAppDatabase(c)?.UserskillDao()
        return userskillsdao!!.isRowIsExist(p)

    }

    //Now its time of User Summary

    fun getUserSummaryObservers():MutableLiveData<List<UserSummaryEntity>>
    {
        return UserSummary
    }
    fun getUserSummary(c:Context)
    {
        val usersummaryDao = RoomAppDb.getAppDatabase(c)?.UsersummaryDao()
        val list=usersummaryDao?.getAllsummary()
        UserSummary.postValue(list)
    }
    fun insertUserSummary(entity: UserSummaryEntity,c:Context)
    {
        val userSummaryDao=RoomAppDb.getAppDatabase(c)?.UsersummaryDao()
        userSummaryDao?.insertUser(entity)
       getUserSummary(c)
    }
    fun deleteUserSummary(entity: UserSummaryEntity,c:Context)
    {
        val userSummaryDao=RoomAppDb.getAppDatabase(c)?.UsersummaryDao()
        userSummaryDao?.deleteUser(entity)
        getUserSummary(c)
    }
    fun UpdateUserSummary(entity: UserSummaryEntity,c:Context)
    {
        val userSummaryDao=RoomAppDb.getAppDatabase(c)?.UsersummaryDao()
        userSummaryDao?.updateUser(entity)
        getUserSummary(c)
    }
    fun isExistUsrSummary(c: Context, p: Int): Boolean {
        val usersummaryDao=RoomAppDb.getAppDatabase(c)?.UsersummaryDao()
        return usersummaryDao!!.isRowIsExist(p)

    }
}