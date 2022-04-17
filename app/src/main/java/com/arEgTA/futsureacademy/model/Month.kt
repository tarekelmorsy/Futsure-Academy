package com.arEgTA.futsureacademy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Month(
    var date:String,
    var totalEvaluation: String,
    var season:String,
    var leader:String,
    var interaction: String,
    var interactionWithLeader: String,
    var attendance: String,
    var quran: String,
    var quiz: String,
    var note:String): Parcelable
