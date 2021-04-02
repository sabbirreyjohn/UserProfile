package com.androidrey.userprofile.view.login

import androidx.lifecycle.MutableLiveData
import com.androidrey.userprofile.database.ProfileDao
import com.androidrey.userprofile.model.Profile

object LoginRepository {
    var profileLivedata = MutableLiveData<Profile>()

    fun getProfileFromDataBase(userName: String, profileDao: ProfileDao): MutableLiveData<Profile> {
        var profile = profileDao.getProfileFromDataBase(userName)
        profileLivedata.postValue(profile)
        return profileLivedata
    }
}