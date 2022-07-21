package com.wawakidss.paydebt.data.di

import com.wawakidss.paydebt.domain.DebtUseCases
import com.wawakidss.paydebt.data.DebtRepositoryImpl
import com.wawakidss.paydebt.domain.DebtRepository
import com.wawakidss.paydebt.data.db.DebtRoomDatabase
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
    fun provideUseCases(repository: DebtRepositoryImpl): DebtUseCases =
        DebtUseCases(repository)
}