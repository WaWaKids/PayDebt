package com.wawakidss.paydebt

import androidx.lifecycle.LiveData
import com.wawakidss.paydebt.data.Debt

class DebtInteractorImpl(private val repository: DebtRepository) : DebtInteractor {
    override suspend fun insertDebt(debt: Debt) {
        repository.insertDebt(debt)
    }

    override fun retrieveDebt(id: Int): LiveData<Debt> {
        return repository.retrieveDebt(id)
    }

    override suspend fun updateItem(
        id: Int,
        name: String,
        ownership: Int,
        debtObject: String,
        dueDate: String,
        repaymentDate: String,
        comment: String
    ) {
        repository.updateItem(id,
            name,
            ownership,
            debtObject,
            dueDate,
            repaymentDate,
            comment)
    }

    override suspend fun deleteItem(debt: Debt) {
        repository.deleteItem(debt)
    }
}