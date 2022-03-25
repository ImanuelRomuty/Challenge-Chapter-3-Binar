package com.example.challenge3withnavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
class SecondFragment : Fragment() {
    private lateinit var inputName                 : EditText
    private lateinit var buttonNavigateToScreen3 : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputName                  = view.findViewById(R.id.input_name_edit_text)
        buttonNavigateToScreen3  = view.findViewById(R.id.navigate_to_third_fragment)
        buttonNavigateToScreen3.setOnClickListener {
            val person = Person(
                name = inputName.text.toString()
            )
            findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToThirdFragment(person = person))
        }
    }



}