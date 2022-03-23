package com.wawakidss.paydebt

import androidx.lifecycle.LiveData
import com.wawakidss.paydebt.data.Debt

interface DebtInteractor {

    suspend fun insertDebt(debt: Debt)

    fun retrieveDebt(id: Int): LiveData<Debt>

    suspend fun updateItem(
        id: Int,
        name: String,
        ownership: Int,
        debtObject: String,
        dueDate: String,
        repaymentDate: String,
        comment: String
    )

    suspend fun deleteItem(debt: Debt)
}