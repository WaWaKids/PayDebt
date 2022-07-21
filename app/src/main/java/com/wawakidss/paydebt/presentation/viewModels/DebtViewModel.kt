package com.wawakidss.paydebt.presentation.viewModels

import androidx.lifecycle.*
import com.wawakidss.paydebt.data.DebtEntity
import com.wawakidss.paydebt.domain.DebtUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebtViewModel @Inject constructor(private val useCases: DebtUseCases) : ViewModel() {

    private val dispatcher = Dispatchers.IO
    lateinit var debt: DebtEntity

    fun insertDebt(debt: DebtEntity): Boolean {
        return if (isEntryValid(debt.name, debt.debtObject)) {
            viewModelScope.launch(dispatcher) {
                useCases.insertDebt(debt)
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
        return useCases.retrieveDebt(id).asLiveData()
    }

    fun updateItem(debt: DebtEntity) : Boolean {
        return if (isEntryValid(debt.name, debt.debtObject)) {
            viewModelScope.launch(dispatcher) {
                useCases.updateItem(debt)
            }
            true
        } else false
    }

    fun deleteItem(debt: DebtEntity) {
        viewModelScope.launch(dispatcher) {
            useCases.deleteItem(debt)
        }
    }
}