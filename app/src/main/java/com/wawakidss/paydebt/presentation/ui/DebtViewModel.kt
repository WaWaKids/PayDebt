package com.wawakidss.paydebt.presentation.ui

import android.app.Application
import androidx.lifecycle.*
import com.wawakidss.paydebt.data.DebtInteractor
import com.wawakidss.paydebt.data.DebtInteractorImpl
import com.wawakidss.paydebt.data.DebtRepositoryImpl
import com.wawakidss.paydebt.domain.DebtEntity
import com.wawakidss.paydebt.domain.db.DebtRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DebtViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DebtRoomDatabase.getDatabase(application).debtDao()
    private val dispatcher = Dispatchers.IO
    private val interactor: DebtInteractor = DebtInteractorImpl(DebtRepositoryImpl(dao))
    val allDebts: LiveData<List<DebtEntity>> = dao.getDebts().asLiveData()

    fun insertDebt(debt: DebtEntity): Boolean {
        return if (isEntryValid(debt.name, debt.debtObject)) {
            viewModelScope.launch(dispatcher) {
                interactor.insertDebt(debt)
            }
            true
        } else false
    }

    private fun isEntryValid(name: String, debtObject: String) : Boolean{
        if (name.isBlank() || debtObject.isBlank())
            return false
        return true
    }

    fun retrieveDebt(id: Int): LiveData<DebtEntity> {
        return interactor.retrieveDebt(id)
    }

    fun updateItem(debt: DebtEntity) : Boolean {
        return if (isEntryValid(debt.name, debt.debtObject)) {
            viewModelScope.launch(dispatcher) {
                interactor.updateItem(debt)
            }
            true
        } else false
    }

    fun deleteItem(debt: DebtEntity) {
        viewModelScope.launch(dispatcher) {
            interactor.deleteItem(debt)
        }
    }
}