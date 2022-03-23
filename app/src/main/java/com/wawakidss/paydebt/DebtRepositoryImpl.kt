package com.wawakidss.paydebt

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.wawakidss.paydebt.data.Debt
import com.wawakidss.paydebt.data.DebtDao

class DebtRepositoryImpl(val debtDao: DebtDao) : DebtRepository {
    override suspend fun insertDebt(debt: Debt) {
        debtDao.insert(debt)
    }

    override fun retrieveDebt(id: Int): LiveData<Debt> {
        return debtDao.getDebt(id).asLiveData()
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
        val updatedItem = getUpdatedDebtEntry(id, name, ownership, debtObject, dueDate, repaymentDate, comment)
        debtDao.update(updatedItem)
    }

    override suspend fun deleteItem(debt: Debt) {
        debtDao.delete(debt)
    }

    private fun getUpdatedDebtEntry(
        id: Int,
        name: String,
        ownership: Int,
        debtObject: String,
        dueDate: String,
        repaymentDate: String,
        comment: String
    ): Debt {
        return Debt(
            id = id,
            name = name,
            ownership = ownership,
            debtObject = debtObject,
            dueDate = dueDate,
            repaymentDate = repaymentDate,
            comment = comment
        )
    }
}