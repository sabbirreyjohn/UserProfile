package com.androidrey.userprofile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androidrey.userprofile.model.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)

abstract class TheDataBase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {

        private var instance: TheDataBase? = null

        @Synchronized
        fun getInstance(context: Context): TheDataBase {
            if (instance == null) {

                instance =
                    Room.databaseBuilder(context, TheDataBase::class.java, "profile_db").allowMainThreadQueries().build()
            }

            return instance!!
        }
    }
}