package com.example.kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.model.TransactionDetails

class TransactionAdapter(private  val RList: List<TransactionDetails>): RecyclerView.Adapter<TransactionAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
      //inflate the list layout and return ListViewHolder
        val  view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_list, parent,false)

        return ListViewHolder(view)
    }

    //bind the data to the registered views
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val transactionDetails = RList[position]
        holder.amt.text = transactionDetails.totalBal.toString()
        holder.date.text = transactionDetails.transactionDate
        holder.type.text = transactionDetails.type
        holder.typeSym.text = transactionDetails.transactionSymStr

    }

    //return total data size
    override fun getItemCount(): Int {
       return RList.size
    }

    //register the views
    class ListViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
//          val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val type: TextView = ItemView.findViewById(R.id.type_name)
        val typeSym: TextView = ItemView.findViewById(R.id.type_sym)
        val date: TextView = ItemView.findViewById(R.id.transaction_date)
        val amt: TextView = ItemView.findViewById(R.id.total_transaction)
    }

}