package com.wawakidss.paydebt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Debt (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "ownership")
    val ownership: Int,     // 0 when "owes to me, 1 when "i owe"
    @ColumnInfo(name = "debt_object")
    val debtObject: String,
    @ColumnInfo(name = "due_date")
    val dueDate: String?,
    @ColumnInfo(name = "repayment_date")
    val repaymentDate: String?,
    @ColumnInfo(name = "comment")
    val comment: String?
)