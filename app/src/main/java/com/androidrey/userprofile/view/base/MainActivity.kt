package com.androidrey.userprofile.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androidrey.userprofile.R
import com.androidrey.userprofile.model.Profile
import com.androidrey.userprofile.view.home.HomeFragment
import com.androidrey.userprofile.view.login.LoginFragment
import com.androidrey.userprofile.view.signup.SignUpFragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), SignUpFragment.OnRegisterListener,
    LoginFragment.OnLoginListener, HomeFragment.OnTimerStoppedListener {

    lateinit var fragment: Fragment
    lateinit var timerDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadLoginScreen()

    }

    override fun onRegistered() {
        loadLoginScreen()
    }

    override fun onLogedIn(profile: Profile) {
        loadHomeScreen(profile)
    }

    override fun onRegisterNowClicked() {
        loadSignUpScreen()
    }

    override fun OnTimerStopped() {
        loadLoginScreen()
    }

    private fun loadSignUpScreen() {
        fragment = SignUpFragment()
        (fragment as SignUpFragment).setOnRegisterListener(this)
        loadScreen(fragment, false)
    }

    private fun loadLoginScreen() {
        fragment = LoginFragment()
        (fragment as LoginFragment).setOnLoginListener(this)
        loadScreen(fragment, false)
    }

    private fun loadHomeScreen(profile: Profile) {
        fragment = HomeFragment()
        var bundle = Bundle()
        bundle.putSerializable("profile", profile)
        fragment.arguments = bundle
        (fragment as HomeFragment).setOnTimerStoppedListener(this)
        loadScreen(fragment, false)
    }

    fun loadScreen(fragment: Fragment, addToStack: Boolean) {
        if (addToStack) {
            supportFragmentManager.beginTransaction().replace(R.id.fr, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fr, fragment)
                .commitAllowingStateLoss()
        }

    }


    override fun onStop() {
        super.onStop()
        timerDisposable = Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
            .take(30)
            .map { v -> 30 - v }
            .subscribe(
                { onNext ->

                },
                { onError ->

                },
                {
                    loadLoginScreen()
                }
            ) { onSubscribe ->


            }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerDisposable.dispose()
    }
}