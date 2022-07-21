package com.wawakidss.paydebt.data

import androidx.lifecycle.LiveData
import com.wawakidss.paydebt.domain.DebtEntity
import kotlinx.coroutines.flow.Flow

interface DebtInteractor {

    suspend fun insertDebt(debtEntity: DebtEntity)

    fun retrieveDebt(id: Int): Flow<DebtEntity>

    fun retreiveAllDebts(): Flow<List<DebtEntity>>

    suspend fun updateItem(debt: DebtEntity)

    suspend fun deleteItem(debtEntity: DebtEntity)
}