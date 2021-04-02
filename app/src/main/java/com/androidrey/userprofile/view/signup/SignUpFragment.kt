package com.androidrey.userprofile.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androidrey.userprofile.databinding.FragmentSignUpBinding
import com.androidrey.userprofile.model.Profile


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding?.root
        binding?.bRegister?.setOnClickListener {
            doRegister()
        }
        return view
    }

    fun doRegister() {
        if (binding?.etFullName?.text.toString().isEmpty() || binding?.etUserName?.text.toString()
                .isEmpty() || binding?.etPassword?.text.toString().isEmpty()
        ) {
            Toast.makeText(context, "Please fill every field", Toast.LENGTH_LONG).show()
        } else {

            val profile = Profile(
                binding?.etFullName?.text.toString(),
                binding?.etUserName?.text.toString(),
                binding?.etPassword?.text.toString()
            )
            signUpViewModel.insertProfileToDataBase(profile).observe(viewLifecycleOwner, {
                if (it) {
                    listener.onRegistered()
                } else {
                    Toast.makeText(context, "User Name already exist", Toast.LENGTH_LONG).show()
                }
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    lateinit var listener: OnRegisterListener

    fun setOnRegisterListener(listener: OnRegisterListener) {
        this.listener = listener
    }

    interface OnRegisterListener {
        fun onRegistered()
    }
}