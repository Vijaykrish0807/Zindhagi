package com.example.blooddonationapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.donor_card.*
import kotlinx.android.synthetic.main.donor_card.view.*
import kotlinx.android.synthetic.main.fragment_list_of_donors.view.*

class ListOfDonors : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var donorarraylist:ArrayList<donor>
    private lateinit var myadapter:Adapter
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_list_of_donors, container, false)

        recyclerView=view.recycler_view
        recyclerView.layoutManager=LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        donorarraylist= arrayListOf()

        myadapter=Adapter(donorarraylist)
        recyclerView.adapter=myadapter


        EventChangeListener()

        return view
    }

    private fun EventChangeListener(){
        db= FirebaseFirestore.getInstance()
        db.collection("DONORS")
            .addSnapshotListener(object :EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if(error!=null){
                        Log.e("Firestore",error.message.toString())
                        return
                    }
                    for(dc:DocumentChange in value?.documentChanges!!){
                        if(dc.type==DocumentChange.Type.ADDED){
                            donorarraylist.add(dc.document.toObject(donor::class.java))
                        }
                    }
                    myadapter.notifyDataSetChanged()
                }
            })
    }
}

