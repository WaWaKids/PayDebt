package com.wawakidss.paydebt.di

import com.wawakidss.paydebt.data.DebtInteractor
import com.wawakidss.paydebt.data.DebtInteractorImpl
import com.wawakidss.paydebt.data.DebtRepositoryImpl
import com.wawakidss.paydebt.domain.DebtRepository
import com.wawakidss.paydebt.domain.db.DebtRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DebtsModule {

    @Provides
    fun provideRepository(database: DebtRoomDatabase): DebtRepository =
        DebtRepositoryImpl(database.debtDao())

    @Provides
    fun provideInteractor(repository: DebtRepositoryImpl): DebtInteractor =
        DebtInteractorImpl(repository)
}