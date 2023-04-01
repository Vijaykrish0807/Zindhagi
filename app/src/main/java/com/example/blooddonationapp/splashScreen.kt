package com.example.blooddonationapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class splashScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_splash_screen, container, false)
        Handler(Looper.myLooper()!!).postDelayed({
              findNavController().navigate(R.id.action_splashScreen_to_login_screen)
        },      3000)
        return view
    }


}