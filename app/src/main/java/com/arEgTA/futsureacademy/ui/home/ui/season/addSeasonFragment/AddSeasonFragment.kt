package com.arEgTA.futsureacademy.ui.home.ui.season.addSeasonFragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentAddSeasonBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.Season
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.addMonthFragment.AddMonthViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddSeasonFragment :
    BaseFragmentHome<FragmentAddSeasonBinding>(FragmentAddSeasonBinding::inflate) {

    private val addSeasonViewModel by lazy {
        AddSeasonViewModel.create(this)
    }
    var cal = Calendar.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

                Toast.makeText(activity, "تم اضافة الموسم بنجاح", Toast.LENGTH_LONG).show()
                binding.edDateAddSeason.text = null
                binding.edTotalAddSeason.text = null
                binding.edSeasonAddSeason.text = null
                binding.edLeaderAddSeason.text = null
                binding.edInteractionAddSeason.text = null
                binding.edAttendanceAddSeason.text = null
                binding.edQuranAddSeason.text = null
                binding.edQuizAddSeason.text = null
                binding.edFeedbackSeason.text = null
                binding.edFinalQuizAddSeason.text = null
                binding.edFinalQuranAddSeason.text = null
                binding.edProjectAddSeason.text = null
                binding.edDiscussionAddSeason.text = null
            }
        }
        binding.edDateAddSeason!!.setFocusable(false)

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the edit text , show DatePickerDialog that is set with OnDateSetListener
        binding.edDateAddSeason!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                activity?.let {
                    DatePickerDialog(
                        it,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }
            }

        })
    }
    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.edDateAddSeason!!.setText( sdf.format(cal.getTime()))
    }
}