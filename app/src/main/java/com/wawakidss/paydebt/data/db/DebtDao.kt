package com.wawakidss.paydebt.data.db

import androidx.room.*
import com.wawakidss.paydebt.data.DebtEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DebtDao {

    @Query("SELECT * from debt ORDER BY id ASC")
    fun getDebts() : Flow<List<DebtEntity>>

    @Query("SELECT * from debt WHERE id = :id")
    fun getDebt(id: Int): Flow<DebtEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(debtEntity: DebtEntity)

    @Update
    suspend fun update(debtEntity: DebtEntity)

    @Delete
    suspend fun delete(debtEntity: DebtEntity)
}