package com.example.challenge3withnavigationcomponent

import android.annotation.SuppressLint
import android.content.Intent.getIntent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlin.random.Random


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

    @SuppressLint("SetTextI18n")
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
        val photo              = arguments?.person.byteArray
        name.text = "Nama anda adalah $patchName"

        if(patchAge!="") {
            val ageTempt = patchAge?.toInt()
            var textFilter: String
            if (ageTempt != null) {
                textFilter = if (ageTempt % 2 == 0) {
                    "Umur anda adalah $patchAge , bernilai genap"
                } else {
                    "Umur anda adalah $patchAge , bernilai ganjil"
                }
                age.text = textFilter
            }
            name.text       = "Nama anda adalah $patchName"
            address.text    = "Alamat anda adalah $patchAddress"
            job.text        = "Pekerjaan anda adalah $patchJob"

            if (photo != null){

                val bmp: Bitmap
                bmp = BitmapFactory.decodeByteArray(photo, 0, photo!!.size)

                Glide.with(this)
                    .load(bmp)
                    .circleCrop()
                    .into(view.findViewById(R.id.hero_image))

            }else{

                val heroList = mutableListOf<Any>(
                "https://i.pinimg.com/564x/79/c1/c0/79c1c02a018fed9bd962ad7324fb16b5.jpg",
                "https://i.pinimg.com/564x/7c/37/d9/7c37d9cdb4c9a2c3f2e58409646b4f60.jpg",
                "https://i.pinimg.com/564x/ea/07/94/ea0794fafb2092656d175349c53646ef.jpg",
                "https://i.pinimg.com/564x/70/24/51/702451cbfa8e8b8b07b49259309e50c0.jpg",
                "https://i.pinimg.com/564x/77/77/e4/7777e4cbcff03de8f31c6546fddef953.jpg"
            )

            val randomIndex   = Random.nextInt(heroList.size)
            val randomElement = heroList[randomIndex]

            Glide.with(this)
                .load(randomElement)
                .circleCrop()
                .into(view.findViewById(R.id.hero_image))
            }
        }
        buttonNavigateToScreen4.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name",patchName)
            findNavController().navigate(R.id.action_thirdFragment_to_fourthFragment,bundle)
        }
    }
}