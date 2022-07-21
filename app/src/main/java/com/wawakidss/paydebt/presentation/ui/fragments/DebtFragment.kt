package com.wawakidss.paydebt.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wawakidss.paydebt.R
import com.wawakidss.paydebt.databinding.FragmentDebtBinding
import com.wawakidss.paydebt.data.DebtEntity
import com.wawakidss.paydebt.presentation.viewModels.DebtViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DebtFragment : Fragment() {

    private lateinit var binding: FragmentDebtBinding
    private val args: DebtFragmentArgs by navArgs()
    private val materialDatePicker = MaterialDatePicker.Builder.datePicker()
    private val viewModel: DebtViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDebtBinding.inflate(inflater, container, false)
        if (args.debtId == -1) binding.deleteAction.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.debtId
        if (id > 0) {
            viewModel.retrieveDebt(id).observe(this.viewLifecycleOwner) { selectedItem ->
                viewModel.debt = selectedItem
                bind(viewModel.debt)
                binding.saveAction.setOnClickListener {
                    if (updateItem())
                        findNavController().navigateUp()
                    else
                        Toast.makeText(this.context, getString(R.string.entry_invalid), Toast.LENGTH_SHORT).show()
                }
                binding.deleteAction.setOnClickListener { showConfirmationDialog() }
            }
        } else {
            binding.saveAction.setOnClickListener{
                if (addNewDebt())
                    findNavController().navigateUp()
                else
                    Toast.makeText(this.context, getString(R.string.entry_invalid), Toast.LENGTH_SHORT).show()
            }
        }

        binding.dueDate.setOnClickListener {
            materialDatePicker.setTitleText(R.string.select_due_date)
            val picker = materialDatePicker.build()
            picker.show(childFragmentManager, "tag")
            picker.addOnPositiveButtonClickListener {
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                binding.dueDate.setText(formatter.format(Date(it)))
            }
        }

        binding.repaymentDate.setOnClickListener {
            materialDatePicker.setTitleText(R.string.select_due_date)
            val picker = materialDatePicker.build()
            picker.show(childFragmentManager, "tag")
            picker.addOnPositiveButtonClickListener {
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                binding.repaymentDate.setText(formatter.format(Date(it)))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    private fun addNewDebt(): Boolean {
        with(binding) {
            return viewModel.insertDebt(
                DebtEntity(
                    name = name.text.toString(),
                    ownership = if (optionIOweTo.isChecked) 1 else 0,
                    debtObject = objectOfDebt.text.toString(),
                    dueDate = dueDate.text.toString(),
                    repaymentDate = repaymentDate.text.toString(),
                    comment = comment.text.toString()
                )
            )
        }

    }

    private fun bind(debtEntity: DebtEntity) {
        with(binding) {
            name.setText(debtEntity.name, TextView.BufferType.SPANNABLE)
            if (debtEntity.ownership == 1) debtOption.check(R.id.option_i_owe_to)
            objectOfDebt.setText(debtEntity.debtObject)
            binding.dueDate.setText(debtEntity.dueDate)
            binding.repaymentDate.setText(debtEntity.repaymentDate)
            binding.comment.setText(debtEntity.comment)
        }
    }

    private fun updateItem(): Boolean{
        with(binding) {
            return viewModel.updateItem(
                DebtEntity(
                    this@DebtFragment.args.debtId,
                    name.text.toString(),
                    if (optionIOweTo.isChecked) 1 else 0,
                    objectOfDebt.text.toString(),
                    dueDate.text.toString(),
                    repaymentDate.text.toString(),
                    comment.text.toString()
                )
            )
        }

    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.deleteItem(viewModel.debt)
                findNavController().navigateUp()
            }
            .show()
    }
}