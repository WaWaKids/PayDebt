package com.wawakidss.paydebt

import android.app.Application
import com.wawakidss.paydebt.domain.db.DebtRoomDatabase

class App : Application() {

    val database: DebtRoomDatabase by lazy { DebtRoomDatabase.getDatabase(this) }
}