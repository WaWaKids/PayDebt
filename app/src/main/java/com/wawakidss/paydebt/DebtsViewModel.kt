package com.wawakidss.paydebt

import androidx.lifecycle.*
import com.wawakidss.paydebt.data.Debt
import com.wawakidss.paydebt.data.DebtDao
import kotlinx.coroutines.launch

class DebtsViewModel(private val debtDao: DebtDao) : ViewModel() {

    private val debtInteractor: DebtInteractor = DebtInteractorImpl(DebtRepositoryImpl(debtDao))
    val allDebts: LiveData<List<Debt>> = debtDao.getDebts().asLiveData()

    fun addNewItem(name: String, ownership: Int, debtObject: String, dueDate: String, repaymentDate: String, comment: String) {
        val newDebt = getNewDebtEntry(name, ownership, debtObject, dueDate, repaymentDate, comment)
        insertDebt(newDebt)
    }

    private fun getNewDebtEntry(name: String, ownership: Int, debtObject: String, dueDate: String, repaymentDate: String, comment: String) : Debt {
        return Debt(
            name = name,
            ownership = ownership,
            debtObject = debtObject,
            dueDate = dueDate,
            repaymentDate = repaymentDate,
            comment = comment
        )
    }

    private fun insertDebt(debt: Debt) {
        viewModelScope.launch {
            debtInteractor.insertDebt(debt)
        }
    }

    fun isEntryValid(name: String, debtObject: String) : Boolean{
        if (name.isNullOrBlank() || debtObject.isNullOrBlank())
            return false
        return true
    }

    fun retrieveDebt(id: Int): LiveData<Debt> {
        return debtInteractor.retrieveDebt(id)
    }

    fun updateItem(
        id: Int,
        name: String,
        ownership: Int,
        debtObject: String,
        dueDate: String,
        repaymentDate: String,
        comment: String
    ) {

        viewModelScope.launch {
            debtInteractor.updateItem(id, name, ownership, debtObject, dueDate, repaymentDate, comment)
        }
    }

    fun deleteItem(debt: Debt) {
        viewModelScope.launch {
            debtInteractor.deleteItem(debt)
        }
    }

}

class DebtsViewModelFactory(private val debtDao: DebtDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DebtsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DebtsViewModel(debtDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}