package com.wawakidss.paydebt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wawakidss.paydebt.data.Debt
import com.wawakidss.paydebt.databinding.FragmentNewDebtBinding
import java.text.SimpleDateFormat
import java.util.*

class NewDebtFragment : Fragment() {

    private lateinit var binding: FragmentNewDebtBinding
    private val args: NewDebtFragmentArgs by navArgs()
    private lateinit var debt: Debt
    private val materialDatePicker = MaterialDatePicker.Builder.datePicker()

    private val viewModel: DebtsViewModel by activityViewModels {
        DebtsViewModelFactory(
            (activity?.application as PayDebtApplication).database.debtDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewDebtBinding.inflate(inflater, container, false)
        if (args.debtId == -1) binding.deleteAction.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.debtId
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

        if (id > 0) {
            viewModel.retrieveDebt(id).observe(this.viewLifecycleOwner) { selectedItem ->
                debt = selectedItem
                bind(debt) }
            } else {
            binding.saveAction.setOnClickListener{
                this.addNewDebt()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    private fun addNewDebt() {
        if (isEntryValid()) {
            viewModel.addNewItem(
                binding.name.text.toString(),
                if (binding.optionIOweTo.isChecked) 1 else 0,
                binding.objectOfDebt.text.toString(),
                binding.dueDate.text.toString(),
                binding.repaymentDate.text.toString(),
                binding.comment.text.toString()
            )
            val action = NewDebtFragmentDirections.actionNewDebtFragmentToDebtListFragment()
            findNavController().navigate(action)
        } else {
            Toast.makeText(this.context, getString(R.string.entry_invalid), Toast.LENGTH_SHORT).show()
        }
    }

    private fun isEntryValid() : Boolean{
        return viewModel.isEntryValid(
            binding.name.text.toString(),
            binding.objectOfDebt.text.toString()
        )
    }

    private fun bind(debt: Debt) {
        binding.apply {
            name.setText(debt.name, TextView.BufferType.SPANNABLE)
            if (debt.ownership == 1) debtOption.check(R.id.option_i_owe_to)
            objectOfDebt.setText(debt.debtObject)
            binding.dueDate.setText(debt.dueDate)
            binding.repaymentDate.setText(debt.repaymentDate)
            binding.comment.setText(debt.comment)
            saveAction.setOnClickListener { updateItem() }
            deleteAction.setOnClickListener { showConfirmationDialog() }
        }
    }

    private fun updateItem() {
        if (isEntryValid()) {
            viewModel.updateItem(
                this.args.debtId,
                binding.name.text.toString(),
                if (binding.optionIOweTo.isChecked) 1 else 0,
                binding.objectOfDebt.text.toString(),
                binding.dueDate.text.toString(),
                binding.repaymentDate.text.toString(),
                binding.comment.text.toString()
            )

            findNavController().navigate(
                NewDebtFragmentDirections.actionNewDebtFragmentToDebtListFragment()
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
                deleteItem()
            }
            .show()
    }

    private fun deleteItem() {
        viewModel.deleteItem(debt)
        findNavController().navigateUp()
    }
}