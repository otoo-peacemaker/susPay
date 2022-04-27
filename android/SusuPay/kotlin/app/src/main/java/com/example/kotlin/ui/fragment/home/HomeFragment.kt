package com.example.kotlin.ui.fragment.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.R
import com.example.kotlin.adapters.TransactionAdapter
import com.example.kotlin.model.TransactionDetails
import com.example.kotlin.model.TransactionType
import com.example.kotlin.databinding.FragmentHomeBinding
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.ui.base.BaseFragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.coroutines.Job

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         //val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            with(binding){
                textView.text = it
            }
        }
        binding.recycler.layoutManager = LinearLayoutManager(this.context)
       //pass data into 4 rows in the recycler
        val transData = ArrayList <TransactionDetails>()
        for (i in 1..4){
            transData.add(
                TransactionDetails(
                TransactionType("Deposit").toString(),
                "12-09-21",
                233456.00,
            )
            )
        }

        for (i in 1..2){
            transData.add(
                TransactionDetails(
                TransactionType("Withdraw").toString(),
                "12-09-21",
                2456.00,
            )
            )
        }

        val adapter = TransactionAdapter(transData)
        binding.recycler.adapter = adapter


        initPieChart()
        setPieData()


    }


    private fun initPieChart() {
        binding.chart.apply {
            setUsePercentValues(true)
            description.text = ""

            //hollow pie chart
            isDrawHoleEnabled = false
            setDrawEntryLabels(false)

            //adding padding
            setExtraOffsets(5f, 10f, 5f, 5f)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.isWordWrapEnabled = true

            //create hole in center
            holeRadius = 58f
            transparentCircleRadius = 61f
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)

            //add text in center
            setDrawCenterText(true)
            centerText = "transactions"


            invalidate()
        }

    }

    private fun setPieData() {
        //pie share
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(72f, "in & out"))
        dataEntries.add(PieEntry(26f, "send"))
        dataEntries.add(PieEntry(2f, "withdraw"))

        //share colors
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))

        val dataSet = PieDataSet(dataEntries, "")
        dataSet.sliceSpace = 3f
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)


    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding {
        TODO("Not yet implemented")
    }

    override fun getFragmentRepository(): UserRepository {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): Class<HomeViewModel> {
        TODO("Not yet implemented")
    }
}