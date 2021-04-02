package com.androidrey.userprofile.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.androidrey.userprofile.R
import com.androidrey.userprofile.databinding.FragmentLoginBinding
import com.androidrey.userprofile.databinding.FragmentSignUpBinding
import com.androidrey.userprofile.model.Profile
import com.androidrey.userprofile.view.signup.SignUpViewModel

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding?.root
        binding?.bRegNow?.setOnClickListener {
            listener.onRegisterNowClicked()
        }
        binding?.bLogin?.setOnClickListener {
            doLogin()
        }
        return view
    }

    private fun doLogin() {
        if (binding?.etUserName?.text.toString().isEmpty() || binding?.etPassword?.text.toString()
                .isEmpty()
        ) {
            Toast.makeText(context, "Please fill every field", Toast.LENGTH_LONG).show()
        } else {
            loginViewModel.getProfileFromDataBase(binding?.etUserName?.text.toString())
                .observe(viewLifecycleOwner, Observer {
                    if (it == null) {
                        Toast.makeText(context, "No such user exists", Toast.LENGTH_LONG).show()
                    } else if (!it.password.contentEquals(binding?.etPassword?.text.toString())) {
                        Toast.makeText(context, "Password doesn't match", Toast.LENGTH_LONG).show()
                    } else {
                        listener.onLogedIn(it)
                    }

                })
        }
    }

    lateinit var listener: OnLoginListener

    fun setOnLoginListener(listener: OnLoginListener) {
        this.listener = listener
    }

    interface OnLoginListener {
        fun onLogedIn(profile: Profile)
        fun onRegisterNowClicked()
    }


}