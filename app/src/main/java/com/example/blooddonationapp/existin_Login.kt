package com.example.blooddonationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_existin__login.*
import kotlinx.android.synthetic.main.fragment_existin__login.view.*

class existin_Login : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_existin__login, container, false)
        val Continue=view.Continue
        auth= FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()
        Continue.setOnClickListener {
            if (checking()){
                val email=email.text.toString()
                val password=password.text.toString()
                val name=name.text.toString()
                val phone=phone.text.toString()
                val user= hashMapOf(
                    "Name" to name,
                    "Phone" to phone,
                    "email" to email
                )
                val Users=db.collection("USERS")
                val query=Users.whereEqualTo("email",email).get()
                    .addOnSuccessListener {
                        task->
                        if(task.isEmpty){
                            auth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(requireActivity()){
                                    task->
                                    if(task.isSuccessful){
                                        Users.document(email).set(user)
                                        findNavController().navigate(R.id.action_existin_Login_to_login_screen)
                                    }
                                    else{
                                        Toast.makeText(activity?.applicationContext,"Authentication Failed", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                        else{
                            Toast.makeText(activity?.applicationContext,"User Already Exists", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_existin_Login_to_login_screen)
                        }
                    }
            }
            else{
                Toast.makeText(activity?.applicationContext,"Enter the Details", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    private fun checking(): Boolean {
        if(name.text.toString().trim { it<=' ' }.isNotEmpty()
            && phone.text.toString().trim { it<=' ' }.isNotEmpty()
            && email.text.toString().trim { it<=' ' }.isNotEmpty()
            && password.text.toString().trim { it<=' ' }.isNotEmpty()){
            return true
        }
        return false
    }
}