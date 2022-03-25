package com.example.challenge3withnavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

class FourthFragment : Fragment() {
    private lateinit var inputAge : EditText
    private lateinit var inputAddress : EditText
    private  lateinit var inputJob : EditText
    private lateinit var buttonNavigateBackToScreen3 : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputAge = view.findViewById(R.id.input_age_edit_text)
        inputAddress = view.findViewById(R.id.input_address_edit_text)
        inputJob       = view.findViewById(R.id.input_job_edit_text)
        buttonNavigateBackToScreen3 = view.findViewById(R.id.navigate_back_to_3_fragment)


        buttonNavigateBackToScreen3.setOnClickListener {
            val person = Person(
                name = arguments?.getString("name"),
                age = inputAge.text.toString(),
                address = inputAddress.text.toString(),
                job = inputJob.text.toString()
            )



            findNavController().navigate(FourthFragmentDirections.actionFourthFragmentToThirdFragment(person=person))
        }

    }

}

//NANIT BERIKAN CONTOH KALAU TIDAK PAKET TEXT