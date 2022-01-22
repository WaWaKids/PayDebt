package com.wawakidss.paydebt

import android.app.Application
import com.wawakidss.paydebt.data.DebtRoomDatabase

class PayDebtApplication : Application() {

    val database: DebtRoomDatabase by lazy { DebtRoomDatabase.getDatabase(this) }
}