package com.androidrey.userprofile.view

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidrey.userprofile.model.Profile
import com.androidrey.userprofile.view.LiveDataTestUtil.getOrAwaitValue
import com.androidrey.userprofile.view.login.LoginViewModel
import com.androidrey.userprofile.view.signup.SignUpViewModel
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest = Config.NONE)
@LooperMode(LooperMode.Mode.PAUSED)
@RunWith(AndroidJUnit4::class)
class DBTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    val loginViewModel = LoginViewModel(ApplicationProvider.getApplicationContext())
    val signUpViewModel = SignUpViewModel(ApplicationProvider.getApplicationContext())

    @Test
    fun testInsertProfileToDataBase() {
        var dummyProfile = Profile("Sabbir Ahmed", "sabbir", "123456")
        val value=signUpViewModel.insertProfileToDataBase(dummyProfile).getOrAwaitValue()
        Assert.assertEquals(true, value)
    }

    @Test
    fun testGetProfileFromDataBase() {
        var dummyProfile = Profile("Sabbir Ahmed", "reyjohn88", "123456")
        signUpViewModel.insertProfileToDataBase(dummyProfile).getOrAwaitValue()
        var dummyUserName = "reyjohn88"
        var dummyFullName = "Sabbir Ahmed"
        var getData = loginViewModel.getProfileFromDataBase(dummyUserName).getOrAwaitValue ()
        assertEquals(dummyFullName, getData?.fullName)
    }
}