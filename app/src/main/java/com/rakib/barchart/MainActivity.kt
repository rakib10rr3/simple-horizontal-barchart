package com.rakib.barchart

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var chart: HorizontalBarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setGraph()
    }

    private fun setGraph() {

        chart = h_chart
        chart.setDrawBarShadow(false)

        val description = Description()
        description.text = ""

        chart.legend.isEnabled = false

        chart.setPinchZoom(true)

        chart.isDoubleTapToZoomEnabled = false


        chart.setDrawValueAboveBar(false)


        //Display the axis on the left (contains the labels 1*, 2* and so on)
        val xAxis = chart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isEnabled = true
        xAxis.setDrawAxisLine(true)
        xAxis.isAvoidFirstLastClippingEnabled



        val yLeft = chart.axisLeft

        //Set the minimum and maximum bar lengths as per the values that they represent
        yLeft.axisMaximum = 900f
        yLeft.axisMinimum = 0f
        yLeft.isEnabled = false

        //Set label count to 5 as we are displaying 5 star rating
        xAxis.labelCount = 5


        val values = arrayOf("Personality", "English Comprehension", "Quantitative Ability", "Logical Ability", "Operations")
        xAxis.valueFormatter = XAxisValueFormatter(values)

        val yRight = chart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.isEnabled = true
        yRight.isGranularityEnabled = true
        yRight.granularity = .5f

        yRight.setDrawZeroLine(true)

        //Set bar entries and add necessary formatting
        setGraphData()

        //Add animation to the graph
        chart.animateY(2000)
    }

    private fun setGraphData() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 270f))
        entries.add(BarEntry(1f, 450f))
        entries.add(BarEntry(2f, 650f))
        entries.add(BarEntry(3f, 770f))
        entries.add(BarEntry(4f, 850f))

        val barDataSet = BarDataSet(entries, "Bar Data Set")

        chart.setDrawBarShadow(true)
        barDataSet.barShadowColor = Color.argb(0, 150, 150, 150)
        barDataSet.color = Color.GREEN

        val data = BarData(barDataSet)

        //Set the bar width
        //Note : To increase the spacing between the bars set the value of barWidth to < 1f
        data.barWidth = .4f

        //Finally set the data and refresh the graph
        chart.data = data
        chart.invalidate()
    }
}

class XAxisValueFormatter(private val values: Array<String>) :
    IAxisValueFormatter {
    override fun getFormattedValue(
        value: Float,
        axis: AxisBase
    ): String { // "value" represents the position of the label on the axis (x or y)
        return values[value.toInt()]
    }

}
