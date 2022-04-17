package com.arEgTA.futsureacademy.ui.home.ui.season

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arEgTA.futsureacademy.databinding.FragmentSeasoneDetailsBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.Season
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.MonthDetailsFragmentArgs
import com.arEgTA.futsureacademy.utils.Constants


class SeasonDetailsFragment : BaseFragmentHome<FragmentSeasoneDetailsBinding>(FragmentSeasoneDetailsBinding::inflate) {
    private val args: SeasonDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var season: Season =args.season
        binding.tvAttendanceSeason.text="%${season.attendance}"
        binding.tvInteractionSeason.text="%${season.interaction}"
        binding.tvDateSeason.text=season.date
        binding.tvQuizSeason.text="%${season.quiz}"
        binding.tvQuranSeason.text="%${season.quran}"
        binding.tvLeaderNameSeason.text=season.leader
        binding.tvSeasonNameSeason.text=season.season
        binding.tvTotalSeason.text="%${season.totalEvaluation}"
        binding.tvNoteSeasonDetails.text=season.note
        binding.tvQuranTestSeason.text="%${season.testQuran}"
        binding.tvFinalQuiz.text="%${season.finalQuiz}"
        binding.tvProject.text="%${season.project}"
        binding.tvDiscussionSeason.text="%${season.discussion}"
        // get user id from sharedPreferences
        val sharedPreferences = activity?.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
        var idUser = sharedPreferences?.getString(Constants.LOGINID, "0")

        if (idUser.equals("7VrTy19p9OOWG8qVXoasfUz9Esh2")||idUser.equals("TsEOWcKTFyZ5glTnYRlRgyhulN62")){
            binding.btUpdateSeason.visibility=View.VISIBLE

        }else{
            binding.btUpdateSeason.visibility=View.GONE
         }
        binding.btUpdateSeason.setOnClickListener {
            findNavController().navigate(SeasonDetailsFragmentDirections.actionSeasoneDetailsFragmentToUpdateSeasonFragment(season))

        }

    }
}