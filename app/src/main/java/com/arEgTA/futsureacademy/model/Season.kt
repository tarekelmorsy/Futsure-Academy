package com.arEgTA.futsureacademy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(var leader:String,var totalEvaluation:String,val season:String,var date:String,var interaction:String,
                  var discussion:String,var attendance:String,var quran:String,var quiz:String,var note:String
                  ,var testQuran:String,var finalQuiz:String,var project:String):
    Parcelable
