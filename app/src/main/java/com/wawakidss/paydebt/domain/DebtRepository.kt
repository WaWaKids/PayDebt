package com.wawakidss.paydebt.domain

import androidx.lifecycle.LiveData
import com.wawakidss.paydebt.domain.DebtEntity

interface DebtRepository {

    suspend fun insertDebt(debtEntity: DebtEntity)

    fun retrieveDebt(id: Int): LiveData<DebtEntity>

    suspend fun updateItem(debt: DebtEntity)

    suspend fun deleteItem(debtEntity: DebtEntity)
}