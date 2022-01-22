package com.wawakidss.paydebt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wawakidss.paydebt.data.Debt
import com.wawakidss.paydebt.databinding.DebtListItemBinding


class DebtListAdapter(private val onDebtClicked: (Debt) -> Unit)
    : ListAdapter<Debt, DebtListAdapter.DebtViewHolder>(DiffCallback) {

    class DebtViewHolder(private var binding: DebtListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(debt: Debt, context: Context) {
                binding.itemName.text = debt.name
                if (debt.ownership == 1) binding.debtOwnership.text = context.getString(R.string.i_owe_him)
                else binding.debtOwnership.text = context.getString(R.string.owes_to_me)
                binding.objectOfDebt.text = debt.debtObject
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        return DebtViewHolder(
            DebtListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onDebtClicked(current)
        }
        holder.bind(current, holder.itemView.context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Debt>() {
            override fun areItemsTheSame(oldItem: Debt, newItem: Debt): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Debt, newItem: Debt): Boolean {
                return oldItem.name == newItem.name && oldItem.debtObject == newItem.debtObject
            }
        }
    }
}