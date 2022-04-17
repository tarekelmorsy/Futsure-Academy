package com.arEgTA.futsureacademy.ui.home.ui.graphSeason

import android.os.Bundle
import android.util.Log
import android.view.View
import com.arEgTA.futsureacademy.databinding.FragmentNotificationsBinding
import com.arEgTA.futsureacademy.model.Score
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.home.ui.graphMonth.GraphMonthViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*


class GraphSeasonFragment : BaseFragmentHome<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate){
    private lateinit var barChart: BarChart
    private val graphSeasonViewModel by lazy {
        GraphSeasonViewModel.create(this)
    }
    private var scoreList = ArrayList<Score>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        graphSeasonViewModel.getSeason()
        barChart = binding.barChart
        graphSeasonViewModel.firebaseScoreSeasonMutableLiveData.observe(viewLifecycleOwner){
            scoreList=it as ArrayList<Score>

       //scoreList = getScoreList()

        initBarChart()


        //now draw bar chart with dynamic data
        val entries: ArrayList<BarEntry> = ArrayList()

        //you can replace this data object with  your custom object
        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries.add(BarEntry(i.toFloat(), score.score.toFloat()))
        }

        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        barChart.data = data

        barChart.invalidate()

    }}

    private fun initBarChart() {


//        hide grid lines
        barChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove right y-axis
        barChart.axisRight.isEnabled = false

        //remove legend
        barChart.legend.isEnabled = false


        //remove description label
        barChart.description.isEnabled = false


        //add animation
        barChart.animateY(3000)

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f

    }


    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            Log.d("TAG", "getAxisLabel: index $index")
            return if (index < scoreList.size) {
                scoreList[index].name
            } else {
                "l;om;"
            }
        }
    }


    // simulate api call
    // we are initialising it directly
    private fun getScoreList(): ArrayList<Score> {
        scoreList.add(Score("fly with us", "56"))
        scoreList.add(Score("Go on", "75"))
        scoreList.add(Score("on fire", "85"))
        scoreList.add(Score("fly with us", "100"))
        scoreList.add(Score("fly with us", "56"))
        scoreList.add(Score("Go on", "75"))
        scoreList.add(Score("on fire", "85"))
        scoreList.add(Score("fly with us", "100"))
        return scoreList
    }


}