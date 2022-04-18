package com.semixtech.cv_resume_builder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.semixtech.cv_resume_builder.db.Dao.UserDao
import com.semixtech.cv_resume_builder.db.Dao.UserHistoryDao
import com.semixtech.cv_resume_builder.db.Dao.UsereducationDao
import com.semixtech.cv_resume_builder.db.Dao.UserskillDao
import com.semixtech.cv_resume_builder.db.Entity.UserEducationEntity
import com.semixtech.cv_resume_builder.db.Entity.UserEntity
import com.semixtech.cv_resume_builder.db.Entity.UserHistoryEntity


@Database(entities = [UserEntity::class,UserHistoryEntity::class,UserEducationEntity::class], version = 4, exportSchema = false)
abstract class RoomAppDb:RoomDatabase() {
    abstract fun UserDao(): UserDao?
    abstract fun UserHistoryDao(): UserHistoryDao?
    abstract fun UsereducationDao():UsereducationDao
    abstract fun UserskillDao():UserskillDao
    companion object
    {
        private var INSTANCE: RoomAppDb? = null


        fun getAppDatabase(context: Context): RoomAppDb? {
            if (INSTANCE == null) {
           INSTANCE= Room.databaseBuilder<RoomAppDb>(
               context.applicationContext,RoomAppDb::class.java,"Resume_Builder"
           ).allowMainThreadQueries().build()

            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }

    }
}