package com.androidrey.userprofile.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidrey.userprofile.model.Profile
import io.reactivex.Single


@Dao
interface ProfileDao {


    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertProfileToDatabase(profile: Profile): Long


    @Query("SELECT * FROM profile_table WHERE userName = :usrName")
    fun getProfileFromDataBase(usrName: String): Profile

    @Query("DELETE FROM profile_table")
    fun deleteAllProfiles(): Single<Int>
}