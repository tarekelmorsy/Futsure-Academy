package com.arEgTA.futsureacademy.ui.home.ui.season

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.Season
import com.arEgTA.futsureacademy.model.repositories.UserRepo
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.MonthlyEvaluationViewModel

class SeasonViewModel(application: Application, var userRepo: UserRepo) :
    AndroidViewModel(application) {


    var firebaseSeasonMutableLiveData: MutableLiveData<List<Season>>

    init {
        firebaseSeasonMutableLiveData = userRepo.firebaseSeasonMutableLiveData

    }

    fun getSeason() {
        userRepo.getSeason()
    }

    fun deleteSeason(date: String) {
        userRepo.deleteSeason(date)
    }


    class Factory(private val application: Application, var userRepo: UserRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SeasonViewModel(application, userRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): SeasonViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                UserRepo(context.context?.applicationContext as Application)
            )
        )[SeasonViewModel::class.java]
    }
}