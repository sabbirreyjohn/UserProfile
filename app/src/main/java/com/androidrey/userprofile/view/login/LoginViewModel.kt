package com.androidrey.userprofile.view.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidrey.userprofile.database.ProfileDao
import com.androidrey.userprofile.database.TheDataBase
import com.androidrey.userprofile.model.Profile

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var theDataBase: TheDataBase = TheDataBase.getInstance(application)
    var profileDao: ProfileDao = theDataBase.profileDao()

    fun getProfileFromDataBase(userName: String): MutableLiveData<Profile> {
        return LoginRepository.getProfileFromDataBase(userName, profileDao)
    }

}