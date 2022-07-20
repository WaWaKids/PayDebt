package com.wawakidss.paydebt.data

import androidx.lifecycle.LiveData
import com.wawakidss.paydebt.domain.DebtEntity
import com.wawakidss.paydebt.domain.DebtRepository

class DebtInteractorImpl(private val repository: DebtRepository) : DebtInteractor {

    override suspend fun insertDebt(debtEntity: DebtEntity) {
        repository.insertDebt(debtEntity)
    }

    override fun retrieveDebt(id: Int): LiveData<DebtEntity> {
        return repository.retrieveDebt(id)
    }

    override suspend fun updateItem(debt: DebtEntity) {
        repository.updateItem(debt)
    }

    override suspend fun deleteItem(debtEntity: DebtEntity) {
        repository.deleteItem(debtEntity)
    }
}