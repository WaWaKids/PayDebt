package com.wawakidss.paydebt.presentation.ui.debtsList

import androidx.lifecycle.*
import com.wawakidss.paydebt.data.DebtInteractor
import com.wawakidss.paydebt.domain.DebtEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebtsListViewModel @Inject constructor(interactor: DebtInteractor) : ViewModel() {

    private var _allDebts: LiveData<List<DebtEntity>> = interactor.retreiveAllDebts().asLiveData()
    val allDebts: LiveData<List<DebtEntity>>
        get() = _allDebts
}