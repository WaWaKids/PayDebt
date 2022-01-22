package com.wawakidss.paydebt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wawakidss.paydebt.data.Debt
import com.wawakidss.paydebt.databinding.FragmentDebtListBinding


class DebtListFragment : Fragment() {

    private lateinit var binding: FragmentDebtListBinding
    private lateinit var debt: Debt

    private val viewModel: DebtsViewModel by activityViewModels {
        DebtsViewModelFactory(
            (activity?.application as PayDebtApplication).database.debtDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDebtListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DebtListAdapter {

            this.findNavController().navigate(
                DebtListFragmentDirections.actionDebtListFragmentToNewDebtFragment(getString(R.string.debt_details), it.id)
            )
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        viewModel.allDebts.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }


        binding.floatingActionButton.setOnClickListener {
            this.findNavController().navigate(DebtListFragmentDirections.actionDebtListFragmentToNewDebtFragment(
                getString(R.string.add_fragment_title), -1))
        }
    }


}