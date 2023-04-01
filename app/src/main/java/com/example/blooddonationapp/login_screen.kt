package com.example.blooddonationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login_screen.*
import kotlinx.android.synthetic.main.fragment_login_screen.view.*

class login_screen : Fragment() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_login_screen, container, false)
        val login=view.login
        val register=view.register
        auth= FirebaseAuth.getInstance()
        register.setOnClickListener {

            findNavController().navigate(R.id.action_login_screen_to_existin_Login)
        }
        login.setOnClickListener {
            if(checking()){
                val email=email.text.toString()
                val password=password.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()){ task->
                        if(task.isSuccessful){
                            Toast.makeText(activity?.applicationContext,"Login Succesful",Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_login_screen_to_optionPage)
                        }
                        else{
                            Toast.makeText(activity?.applicationContext,"Wrong Details",Toast.LENGTH_SHORT).show()
                        }
                }
            }
            else{
                Toast.makeText(activity?.applicationContext,"Enter the Details",Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
    private fun checking(): Boolean {
        if(email.text.toString().trim { it<=' ' }.isNotEmpty()
            && password.text.toString().trim { it<=' ' }.isNotEmpty()){
            return true
        }
        return false
    }
}

