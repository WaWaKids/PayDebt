package com.wawakidss.paydebt.data

import com.wawakidss.paydebt.domain.DebtEntity
import com.wawakidss.paydebt.domain.DebtRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DebtInteractorImpl @Inject constructor(val repository: DebtRepository) : DebtInteractor {

    private val dispatcher = Dispatchers.IO

    override fun retreiveAllDebts(): Flow<List<DebtEntity>> = repository.retreiveAllDebts()

    override fun retrieveDebt(id: Int): Flow<DebtEntity> = repository.retrieveDebt(id)

    override suspend fun insertDebt(debtEntity: DebtEntity) {
        withContext(dispatcher) {
            repository.insertDebt(debtEntity)
        }
    }

    override suspend fun updateItem(debt: DebtEntity) {
        withContext(dispatcher) {
            repository.updateItem(debt)
        }
    }

    override suspend fun deleteItem(debtEntity: DebtEntity) {
        withContext(dispatcher) {
            repository.deleteItem(debtEntity)
        }
    }
}