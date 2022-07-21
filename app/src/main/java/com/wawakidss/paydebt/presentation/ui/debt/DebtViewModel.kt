package com.wawakidss.paydebt.presentation.ui.debt

import androidx.lifecycle.*
import com.wawakidss.paydebt.data.DebtInteractor
import com.wawakidss.paydebt.domain.DebtEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebtViewModel @Inject constructor(private val interactor: DebtInteractor) : ViewModel() {

    private val dispatcher = Dispatchers.IO

    lateinit var debt: DebtEntity

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
        return interactor.retrieveDebt(id).asLiveData()
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