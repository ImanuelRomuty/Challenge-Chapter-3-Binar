package com.example.challenge3withnavigationcomponent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val byteArray: ByteArray? = null,
    val name : String? = "",
    val age : String? = "",
    val address : String? = "",
    val job : String? = ""

):Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (!byteArray.contentEquals(other.byteArray)) return false
        if (name != other.name) return false
        if (age != other.age) return false
        if (address != other.address) return false
        if (job != other.job) return false

        return true
    }

    override fun hashCode(): Int {
        var result = byteArray.contentHashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (age?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (job?.hashCode() ?: 0)
        return result
    }
}
