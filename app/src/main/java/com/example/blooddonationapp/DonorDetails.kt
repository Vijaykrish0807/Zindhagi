package com.example.blooddonationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_donor_details.*
import kotlinx.android.synthetic.main.fragment_donor_details.view.*

class DonorDetails : Fragment() {
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_donor_details, container, false)
        val Continue=view.Continue
        db= FirebaseFirestore.getInstance()
        Continue.setOnClickListener {
            if(checking()){
                val name=name.text.toString()
                val phone=phone.text.toString()
                val location=location.text.toString()
                val bgroup=bgroup.text.toString()
                val donor= hashMapOf(
                    "Name" to name,
                    "Phone" to phone,
                    "Location" to location,
                    "Bgroup" to bgroup
                )
                val Donors=db.collection("DONORS")
                Donors.document(name).set(donor)
                Toast.makeText(activity?.applicationContext,"Donor Successfully added to Database",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_donorDetails_to_donorThankyou)
            }
            else{
                Toast.makeText(activity?.applicationContext,"Fill the Fiels Correctly",Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
    private fun checking():Boolean{
        if(name.text.toString().trim { it<=' '}.isNotEmpty()
            && phone.text.toString().trim { it<=' '}.isNotEmpty()
        && location.text.toString().trim { it<=' '}.isNotEmpty()
            && bgroup.text.toString().trim { it<=' '}.isNotEmpty()) {
            return true
        }
        return false
    }
}