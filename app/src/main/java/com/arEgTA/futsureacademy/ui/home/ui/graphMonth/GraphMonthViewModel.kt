package com.arEgTA.futsureacademy.ui.home.ui.graphMonth

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arEgTA.futsureacademy.model.Score
import com.arEgTA.futsureacademy.model.repositories.UserRepo

class GraphMonthViewModel (application: Application, var userRepo: UserRepo) : AndroidViewModel(application) {


    var firebaseScoreMutableLiveData = MutableLiveData<List<Score>>()

    init {
        firebaseScoreMutableLiveData=userRepo.firebaseScoreMonthMutableLiveData

    }
    fun getMonth(){
        userRepo.getMonth()
    }



    class Factory(private val application: Application, var userRepo: UserRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GraphMonthViewModel(application,userRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): GraphMonthViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                UserRepo(context.context?.applicationContext as Application)
            )
        )[GraphMonthViewModel::class.java]
    }


}