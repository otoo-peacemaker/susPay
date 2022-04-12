package com.example.kotlin.ui.fragment.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.adapters.TransactionAdapter
import com.example.kotlin.dao.TransactionDetails
import com.example.kotlin.databinding.FragmentHomeBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }

        binding.recycler.layoutManager = LinearLayoutManager(this.context)
       //pass data into 4 rows in the recycler
        val transData = ArrayList <TransactionDetails>()
        for (i in 1..4){
            transData.add(
                TransactionDetails(
                "Deposit",
                "12-09-21",
                233456.00,
                "D"
            )
            )
        }

        for (i in 1..2){
            transData.add(
                TransactionDetails(
                "Withdraw",
                "12-09-21",
                2456.00,
                "W"
            )
            )
        }

        val adapter = TransactionAdapter(transData)
        binding.recycler.adapter = adapter


        initPieChart()
        setPieData()


        return root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}