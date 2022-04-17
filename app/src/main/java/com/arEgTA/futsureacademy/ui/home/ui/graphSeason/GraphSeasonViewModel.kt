package com.arEgTA.futsureacademy.ui.home.ui.graphSeason

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.arEgTA.futsureacademy.model.Score
import com.arEgTA.futsureacademy.model.repositories.UserRepo
import com.arEgTA.futsureacademy.ui.home.ui.graphMonth.GraphMonthViewModel

class GraphSeasonViewModel(application: Application, var userRepo: UserRepo) : AndroidViewModel(application) {


    var firebaseScoreSeasonMutableLiveData = MutableLiveData<List<Score>>()

    init {
        firebaseScoreSeasonMutableLiveData=userRepo.firebaseScoreSeasonMutableLiveData

    }
    fun getSeason(){
        userRepo.getSeason()
    }



    class Factory(private val application: Application, var userRepo: UserRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GraphSeasonViewModel(application,userRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): GraphSeasonViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                UserRepo(context.context?.applicationContext as Application)
            )
        )[GraphSeasonViewModel::class.java]
    }


}