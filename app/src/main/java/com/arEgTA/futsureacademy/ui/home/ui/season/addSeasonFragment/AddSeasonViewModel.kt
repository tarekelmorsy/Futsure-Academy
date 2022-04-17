package com.arEgTA.futsureacademy.ui.home.ui.season.addSeasonFragment

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.Season
import com.arEgTA.futsureacademy.model.repositories.UserRepo
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.addMonthFragment.AddMonthViewModel

class AddSeasonViewModel (application: Application, var userRepo: UserRepo) : AndroidViewModel(application) {


    fun setSeason(season: Season){
        userRepo.setSeason(season)
    }



    class Factory(private val application: Application, var userRepo: UserRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddSeasonViewModel(application,userRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): AddSeasonViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                UserRepo(context.context?.applicationContext as Application)
            )
        )[AddSeasonViewModel::class.java]
    }
}