package com.arEgTA.futsureacademy.ui.home.ui.season.updateSeason

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentAddSeasonBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.Season
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.updateMonth.UpdateMonthFragmentArgs
import com.arEgTA.futsureacademy.ui.home.ui.season.addSeasonFragment.AddSeasonViewModel

class UpdateSeasonFragment :
    BaseFragmentHome<FragmentAddSeasonBinding>(FragmentAddSeasonBinding::inflate) {
    private val args: UpdateSeasonFragmentArgs by navArgs()

    private val addSeasonViewModel by lazy {
        AddSeasonViewModel.create(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var month: Season = args.season
        binding.edDateAddSeason.setText(month.date)
        binding.edTotalAddSeason.setText(month.totalEvaluation)
        binding.edSeasonAddSeason.setText(month.season)
        binding.edLeaderAddSeason.setText(month.leader)
        binding.edInteractionAddSeason.setText(month.interaction)
         binding.edAttendanceAddSeason.setText(month.attendance)
        binding.edQuranAddSeason.setText(month.quran)
        binding.edQuizAddSeason.setText(month.quiz)
        binding.edFeedbackSeason.setText(month.note)
        binding.edDiscussionAddSeason.setText(month.discussion)
        binding.edFinalQuizAddSeason.setText(month.finalQuiz)
        binding.edFinalQuranAddSeason.setText(month.testQuran)
        binding.edProjectAddSeason.setText(month.project)

        binding.edDateAddSeason.setFocusable(false)

        binding.btDoneSeason.setOnClickListener {
            if (binding.edDateAddSeason.text.toString().isNullOrEmpty()) {
                binding.edDateAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edTotalAddSeason.text.toString().isNullOrEmpty()) {
                binding.edTotalAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edSeasonAddSeason.text.toString().isNullOrEmpty()) {
                binding.edSeasonAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edLeaderAddSeason.text.toString().isNullOrEmpty()) {
                binding.edLeaderAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edInteractionAddSeason.text.toString().isNullOrEmpty()) {
                binding.edInteractionAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edProjectAddSeason.text.toString().isNullOrEmpty()) {
                binding.edProjectAddSeason.error =
                    activity?.getString(R.string.required_field)
            } else if (binding.edAttendanceAddSeason.text.toString().isNullOrEmpty()) {
                binding.edAttendanceAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edQuranAddSeason.text.toString().isNullOrEmpty()) {
                binding.edQuranAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edQuizAddSeason.text.toString().isNullOrEmpty()) {
                binding.edQuizAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edFeedbackSeason.text.toString().isNullOrEmpty()) {
                binding.edFeedbackSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edFinalQuizAddSeason.text.toString().isNullOrEmpty()) {
                binding.edFinalQuizAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edFinalQuranAddSeason.text.toString().isNullOrEmpty()) {
                binding.edQuizAddSeason.error = activity?.getString(R.string.required_field)
            } else if (binding.edDiscussionAddSeason.text.toString().isNullOrEmpty()) {
                binding.edDiscussionAddSeason.error = activity?.getString(R.string.required_field)
            } else {
                var date = binding.edDateAddSeason.text.toString()
                var totalEvaluation = binding.edTotalAddSeason.text.toString()
                var season = binding.edSeasonAddSeason.text.toString()
                var leader = binding.edLeaderAddSeason.text.toString()
                var interaction = binding.edInteractionAddSeason.text.toString()
                var attendance = binding.edAttendanceAddSeason.text.toString()
                var quran = binding.edQuranAddSeason.text.toString()
                var quiz = binding.edQuizAddSeason.text.toString()
                var note = binding.edFeedbackSeason.text.toString()
                var finalQuiz = binding.edFinalQuizAddSeason.text.toString()
                var finalQuran = binding.edFinalQuranAddSeason.text.toString()
                var project = binding.edProjectAddSeason.text.toString()
                var discussion = binding.edDiscussionAddSeason.text.toString()

                addSeasonViewModel.setSeason(
                    Season(
                        leader, totalEvaluation, season, date, interaction,
                        discussion, attendance, quran, quiz, note, finalQuran, finalQuiz, project
                    )
                )

                Toast.makeText(activity, "تم تعديل الموسم بنجاح", Toast.LENGTH_LONG).show()

            }
        }
    }
}