package com.wawakidss.paydebt.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.wawakidss.paydebt.domain.DebtEntity
import com.wawakidss.paydebt.domain.DebtRepository
import com.wawakidss.paydebt.domain.db.DebtDao

class DebtRepositoryImpl(val dao: DebtDao) : DebtRepository {
    override suspend fun insertDebt(debtEntity: DebtEntity) {
        dao.insert(debtEntity)
    }

    override fun retrieveDebt(id: Int): LiveData<DebtEntity> {
        return dao.getDebt(id).asLiveData()
    }

    override suspend fun updateItem(debt: DebtEntity) {
        dao.update(debt)
    }

    override suspend fun deleteItem(debtEntity: DebtEntity) {
        dao.delete(debtEntity)
    }
}