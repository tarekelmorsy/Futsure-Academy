package com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.addMonthFragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentAddMonthBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.MonthDetailsFragmentArgs
import java.text.SimpleDateFormat
import java.util.*

class AddMonthFragment :
    BaseFragmentHome<FragmentAddMonthBinding>(FragmentAddMonthBinding::inflate) {

    private val addMonthViewModel by lazy {
        AddMonthViewModel.create(this)
    }
    var cal = Calendar.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check from edit texts
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
                addMonthViewModel.setMonth(
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
                Toast.makeText(activity,"تم اضافة الشهر بنجاح",Toast.LENGTH_LONG).show()
                binding.edDateAddMonth.text = null
                binding.edTotalAddMonth.text = null
                binding.edSeasonAddMonth.text = null
                binding.edLeaderAddMonth.text = null
                binding.edInteractionAddMonth.text = null
                binding.edInteractionWhithLeaderAddMonth.text = null
                binding.edAttendanceAddMonth.text = null
                binding.edQuranAddMonth.text = null
                binding.edQuizAddMonth.text = null
                binding.edFeedbackMonth.text = null
            }
        }
        binding.edDateAddMonth!!.setFocusable(false)

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
        binding.edDateAddMonth!!.setOnClickListener(object : View.OnClickListener {
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
        binding.edDateAddMonth!!.setText( sdf.format(cal.getTime()))
    }
}