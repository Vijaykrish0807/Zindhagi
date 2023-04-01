package com.example.blooddonationapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_option_page.view.*

class OptionPage : Fragment() {
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_option_page, container, false)
        val donor = view.donor
        val acceptor = view.acceptor
        donor.setOnClickListener {
            findNavController().navigate(R.id.action_optionPage_to_donorDetails)
        }
        acceptor.setOnClickListener {
            findNavController().navigate(R.id.action_optionPage_to_listOfDonors)
        }
        return view
    }
}
