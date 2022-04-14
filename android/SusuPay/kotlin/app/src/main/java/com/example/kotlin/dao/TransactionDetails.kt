package com.example.kotlin.dao

import io.reactivex.internal.operators.flowable.FlowableDelaySubscriptionOther

data class TransactionDetails(
    val type: String? = null,
    val transactionDate: String? = null,
    val totalBal: Double? = null,
    val transactionSymStr: String? = type!!.substring(0,0)

)

data class TransactionType(
    val deposit: String? = null,
    val withdraw: String? = null,
    val other: String? = null
)
