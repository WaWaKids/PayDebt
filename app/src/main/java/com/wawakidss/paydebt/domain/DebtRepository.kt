package com.wawakidss.paydebt.domain

import com.wawakidss.paydebt.data.DebtEntity
import kotlinx.coroutines.flow.Flow

interface DebtRepository {

    fun retreiveAllDebts(): Flow<List<DebtEntity>>

    fun retrieveDebt(id: Int): Flow<DebtEntity>

    suspend fun insertDebt(debtEntity: DebtEntity)

    suspend fun updateItem(debt: DebtEntity)

    suspend fun deleteItem(debtEntity: DebtEntity)
}