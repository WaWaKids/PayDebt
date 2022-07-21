package com.wawakidss.paydebt.domain

import com.wawakidss.paydebt.data.DebtEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DebtUseCases @Inject constructor(val repository: DebtRepository){

    private val dispatcher = Dispatchers.IO

    fun retreiveAllDebts(): Flow<List<DebtEntity>> = repository.retreiveAllDebts()

    fun retrieveDebt(id: Int): Flow<DebtEntity> = repository.retrieveDebt(id)

    suspend fun insertDebt(debtEntity: DebtEntity) {
        withContext(dispatcher) {
            repository.insertDebt(debtEntity)
        }
    }

    suspend fun updateItem(debt: DebtEntity) {
        withContext(dispatcher) {
            repository.updateItem(debt)
        }
    }

    suspend fun deleteItem(debtEntity: DebtEntity) {
        withContext(dispatcher) {
            repository.deleteItem(debtEntity)
        }
    }
}