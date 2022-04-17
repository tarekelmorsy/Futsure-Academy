package com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.updateMonth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentAddMonthBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome

class UpdateMonthFragment :
    BaseFragmentHome<FragmentAddMonthBinding>(FragmentAddMonthBinding::inflate) {
    private val args: UpdateMonthFragmentArgs by navArgs()

    private val updateMonthViewModel by lazy {
        UpdateMonthViewModel.create(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            var month: Month = args.month

                binding.edDateAddMonth.setText(month.date)
                binding.edTotalAddMonth.setText(month.totalEvaluation)
                binding.edSeasonAddMonth.setText(month.season)
                binding.edLeaderAddMonth.setText(month.leader)
                binding.edInteractionAddMonth.setText(month.interaction)
                binding.edInteractionWhithLeaderAddMonth.setText(month.interactionWithLeader)
                binding.edAttendanceAddMonth.setText(month.attendance)
                binding.edQuranAddMonth.setText(month.quran)
                binding.edQuizAddMonth.setText(month.quiz)
                binding.edFeedbackMonth.setText(month.note)
        binding.edDateAddMonth.setFocusable(false)



        binding.btDoneAddMonth.setOnClickListener {
            if (binding.edDateAddMonth.text.toString().isNullOrEmpty()) {
                binding.edDateAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edTotalAddMonth.text.toString().isNullOrEmpty()) {
                binding.edTotalAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edSeasonAddMonth.text.toString().isNullOrEmpty()) {
                binding.edSeasonAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edLeaderAddMonth.text.toString().isNullOrEmpty()) {
                binding.edLeaderAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edInteractionAddMonth.text.toString().isNullOrEmpty()) {
                binding.edInteractionAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edInteractionWhithLeaderAddMonth.text.toString().isNullOrEmpty()) {
                binding.edInteractionWhithLeaderAddMonth.error =
                    activity?.getString(R.string.required_field)
            } else if (binding.edAttendanceAddMonth.text.toString().isNullOrEmpty()) {
                binding.edAttendanceAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edQuranAddMonth.text.toString().isNullOrEmpty()) {
                binding.edQuranAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edQuizAddMonth.text.toString().isNullOrEmpty()) {
                binding.edQuizAddMonth.error = activity?.getString(R.string.required_field)
            } else if (binding.edFeedbackMonth.text.toString().isNullOrEmpty()) {
                binding.edFeedbackMonth.error = activity?.getString(R.string.required_field)
            } else {
                var date = binding.edDateAddMonth.text.toString()
                var totalEvaluation = binding.edTotalAddMonth.text.toString()
                var season = binding.edSeasonAddMonth.text.toString()
                var leader = binding.edLeaderAddMonth.text.toString()
                var interaction = binding.edInteractionAddMonth.text.toString()
                var interactionWithLeader =
                    binding.edInteractionWhithLeaderAddMonth.text.toString()
                var attendance = binding.edAttendanceAddMonth.text.toString()
                var quran = binding.edQuranAddMonth.text.toString()
                var quiz = binding.edQuizAddMonth.text.toString()
                var note = binding.edFeedbackMonth.text.toString()
                updateMonthViewModel.updateMonth(
                    Month(
                        date,
                        totalEvaluation,
                        season,
                        leader,
                        interaction,
                        interactionWithLeader,
                        attendance,
                        quran,
                        quiz,
                        note
                    )
                )
                Toast.makeText(activity,"تم تعديل الشهر بنجاح", Toast.LENGTH_LONG).show()

            }

        }
    }
}