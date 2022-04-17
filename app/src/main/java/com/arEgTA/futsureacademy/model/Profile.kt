package com.arEgTA.futsureacademy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Profile(
    var name:String,
    var email:String,
    var leaderName:String,
    var age: String,
    var birthday:String,
    var groupName:String,
    var dateOfJoin:String,): Parcelable
