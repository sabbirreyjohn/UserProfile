package com.androidrey.userprofile.view.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidrey.userprofile.database.ProfileDao
import com.androidrey.userprofile.database.TheDataBase
import com.androidrey.userprofile.model.Profile
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    var theDataBase: TheDataBase = TheDataBase.getInstance(application)
    var profileDao: ProfileDao = theDataBase.profileDao()

    fun insertProfileToDataBase(profile: Profile): MutableLiveData<Boolean> {
        return SignUpRepository.insertProfileToDataBase(profileDao, profile)
    }
}