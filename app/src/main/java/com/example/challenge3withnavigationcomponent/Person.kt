package com.example.challenge3withnavigationcomponent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val name : String? = "",
    val age : String? = "",
    val address : String? = "",
    val job : String? = ""
):Parcelable
