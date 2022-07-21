package com.wawakidss.paydebt.data

import com.wawakidss.paydebt.domain.DebtRepository
import com.wawakidss.paydebt.data.db.DebtDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DebtRepositoryImpl @Inject constructor(val dao: DebtDao) : DebtRepository {

    private val dispatcher = Dispatchers.IO

    override fun retrieveDebt(id: Int) = dao.getDebt(id)

    override fun retreiveAllDebts() = dao.getDebts()

    override suspend fun insertDebt(debtEntity: DebtEntity) {
        withContext(dispatcher) {
            dao.insert(debtEntity)
        }
    }

    override suspend fun updateItem(debt: DebtEntity) {
        withContext(dispatcher) {
            dao.update(debt)
        }
    }

    override suspend fun deleteItem(debtEntity: DebtEntity) {
        withContext(dispatcher) {
            dao.delete(debtEntity)
        }
    }
}