package com.wawakidss.paydebt.data

import android.content.ClipData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DebtDao {

    @Query("SELECT * from debt ORDER BY id ASC")
    fun getDebts() : Flow<List<Debt>>

    @Query("SELECT * from debt WHERE id = :id")
    fun getDebt(id: Int): Flow<Debt>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(debt: Debt)

    @Update
    suspend fun update(debt: Debt)

    @Delete
    suspend fun delete(debt: Debt)
}