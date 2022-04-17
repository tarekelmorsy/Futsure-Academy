package com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arEgTA.futsureacademy.databinding.FragmentMonthDetailsBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.home.ui.season.SeasonDetailsFragmentDirections
import com.arEgTA.futsureacademy.utils.Constants

class MonthDetailsFragment : BaseFragmentHome<FragmentMonthDetailsBinding>(FragmentMonthDetailsBinding::inflate) {
    private val args: MonthDetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var month:Month=args.details
        month.apply {

            binding.tvAttendanceMonth.text="%$attendance"
            binding.tvInteractionMonth.text="%$interaction"
            binding.tvInteractionLeaderMonth.text="%$interactionWithLeader"
            binding.tvQuizMonth.text="%$quiz"
            binding.tvQuranMonth.text="%$quran"
            binding.tvLeaderName.text=leader
            binding.tvSeasonNameMonth.text=season
            binding.tvTotalMonth.text="%$totalEvaluation"
            binding.tvNoteMonthDetails.text=note
            binding.tvDateMonth.text=date
        }

// get user id from sharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
        var idUser = sharedPreferences.getString(Constants.LOGINID, "0")

        if (idUser.equals("7VrTy19p9OOWG8qVXoasfUz9Esh2")||idUser.equals("TsEOWcKTFyZ5glTnYRlRgyhulN62")){
            binding.btUpdateMonth.visibility=View.VISIBLE

        }else{
            binding.btUpdateMonth.visibility=View.GONE


        }

        binding.btUpdateMonth.setOnClickListener {
            findNavController().navigate(MonthDetailsFragmentDirections.actionMonthDetailsToUpdateMonthFragment(month))

        }
    }
}