package com.androidrey.userprofile.view.signup

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import com.androidrey.userprofile.database.ProfileDao
import com.androidrey.userprofile.model.Profile
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

object SignUpRepository {
    var booleanData = MutableLiveData<Boolean>()
    fun insertProfileToDataBase(
        profileDao: ProfileDao,
        profile: Profile
    ): MutableLiveData<Boolean> {
        try {
            var number = profileDao.insertProfileToDatabase(profile)
            booleanData.postValue(number > -1)
        } catch (exception: SQLiteConstraintException) {
            booleanData.postValue(false)
        } finally {
            return booleanData
        }
    }
}