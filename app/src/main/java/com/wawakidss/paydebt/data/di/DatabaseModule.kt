package com.wawakidss.paydebt.data.di

import android.content.Context
import com.wawakidss.paydebt.App
import com.wawakidss.paydebt.data.db.DebtDao
import com.wawakidss.paydebt.data.db.DebtRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDao(database: DebtRoomDatabase): DebtDao {
        return database.debtDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DebtRoomDatabase {
        return DebtRoomDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext appContext: Context) = appContext as App
}