package com.example.challenge3withnavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ThirdFragment : Fragment() {
    private val arguments : ThirdFragmentArgs by navArgs()


    private lateinit var name                   : TextView
    private lateinit var age                    : TextView
    private lateinit var address                : TextView
    private lateinit var job                    : TextView
    private lateinit var buttonNavigateToScreen4 : Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name                    = view.findViewById(R.id.name_text_view)
        age                     = view.findViewById(R.id.age_text_view)
        address                 = view.findViewById(R.id.address_text_view)
        job                     = view.findViewById(R.id.job_text_view)
        buttonNavigateToScreen4  = view.findViewById(R.id.navigate_to_fourth_fragment)

        val patchName           = arguments?.person.name
        val patchAge            = arguments?.person.age
        val patchAddress        = arguments?.person.address
        val patchJob            = arguments?.person.job
        name.text = "Nama anda adalah $patchName"


        if(patchAge!=""){
            val ageTempt = patchAge?.toInt()
            var textFilter : String
            if (ageTempt!=null){
                textFilter = if(ageTempt%2==0){
                    "Umur anda adalah $patchAge , bernilai genap"
                }else{
                    "Umur anda adalah $patchAge , bernilai ganjil"
                }
                age.text  = textFilter
            }
            name.text = "Nama anda adalah $patchName"
            address.text = "Alamat anda adalah $patchAddress"
            job.text    = "Pekerjaan anda adalah $patchJob"
        }



        buttonNavigateToScreen4.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name",patchName)
            findNavController().navigate(R.id.action_thirdFragment_to_fourthFragment,bundle)
        }

    }


}