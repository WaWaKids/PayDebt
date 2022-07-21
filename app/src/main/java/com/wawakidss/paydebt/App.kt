package com.wawakidss.paydebt

import android.app.Application
import com.wawakidss.paydebt.domain.db.DebtRoomDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    val database: DebtRoomDatabase by lazy { DebtRoomDatabase.getDatabase(this) }
}