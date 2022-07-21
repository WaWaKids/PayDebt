package com.wawakidss.paydebt.presentation.ui.debtsList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wawakidss.paydebt.R
import com.wawakidss.paydebt.databinding.DebtListItemBinding
import com.wawakidss.paydebt.domain.DebtEntity


class DebtListAdapter(private val onDebtClicked: (DebtEntity) -> Unit)
    : ListAdapter<DebtEntity, DebtListAdapter.DebtViewHolder>(DiffCallback) {

    class DebtViewHolder(private var binding: DebtListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(debtEntity: DebtEntity, context: Context) {
                binding.itemName.text = debtEntity.name
                if (debtEntity.ownership == 1) binding.debtOwnership.text = context.getString(R.string.i_owe_him)
                else binding.debtOwnership.text = context.getString(R.string.owes_to_me)
                binding.objectOfDebt.text = debtEntity.debtObject
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
        private val DiffCallback = object : DiffUtil.ItemCallback<DebtEntity>() {
            override fun areItemsTheSame(oldItem: DebtEntity, newItem: DebtEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DebtEntity, newItem: DebtEntity): Boolean {
                return oldItem.name == newItem.name && oldItem.debtObject == newItem.debtObject
            }
        }
    }
}