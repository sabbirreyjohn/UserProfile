package com.androidrey.userprofile.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androidrey.userprofile.R
import com.androidrey.userprofile.databinding.FragmentHomeBinding
import com.androidrey.userprofile.databinding.FragmentLoginBinding
import com.androidrey.userprofile.model.Profile
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    lateinit var timerDisposable: Disposable
    lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profile = arguments?.getSerializable("profile") as Profile
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root
        binding?.tvFullName?.text = "Welcome ${profile.fullName}"
        binding?.bLogout?.setOnClickListener {
            listener.OnTimerStopped()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        timerDisposable = Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
            .take(10)
            .map { v -> 10 - v }
            .subscribe(
                { onNext ->

                },
                { onError ->

                },
                {
                    listener.OnTimerStopped()
                }
            ) { onSubscribe ->


            }
    }

    override fun onPause() {
        super.onPause()
        timerDisposable.dispose()
    }


    lateinit var listener: OnTimerStoppedListener

    fun setOnTimerStoppedListener(listener: OnTimerStoppedListener) {
        this.listener = listener
    }

    interface OnTimerStoppedListener {
        fun OnTimerStopped()
    }
}