package com.wawakidss.paydebt.presentation.viewModels

import androidx.lifecycle.*
import com.wawakidss.paydebt.data.DebtEntity
import com.wawakidss.paydebt.domain.DebtUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DebtsListViewModel @Inject constructor(useCases: DebtUseCases) : ViewModel() {

    private var _allDebts: LiveData<List<DebtEntity>> = useCases.retreiveAllDebts().asLiveData()
    val allDebts: LiveData<List<DebtEntity>>
        get() = _allDebts
}