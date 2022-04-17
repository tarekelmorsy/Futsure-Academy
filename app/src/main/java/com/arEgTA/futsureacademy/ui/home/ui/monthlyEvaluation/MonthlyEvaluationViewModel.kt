package com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.repositories.UserRepo
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.addMonthFragment.AddMonthViewModel

class MonthlyEvaluationViewModel (application: Application, var userRepo: UserRepo) : AndroidViewModel(application) {


    var firebaseMonthsMutableLiveData: MutableLiveData<List<Month>>

    init {
        firebaseMonthsMutableLiveData=userRepo.firebaseMonthsMutableLiveData

    }
    fun getMonth(){
        userRepo.getMonth()
    }
    fun deleteMonth(date:String){
        userRepo.deleteMonth(date)
    }



    class Factory(private val application: Application, var userRepo: UserRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MonthlyEvaluationViewModel(application,userRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): MonthlyEvaluationViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                UserRepo(context.context?.applicationContext as Application)
            )
        )[MonthlyEvaluationViewModel::class.java]
    }
}