package com.wawakidss.paydebt.presentation.ui.debtsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wawakidss.paydebt.R
import com.wawakidss.paydebt.databinding.FragmentDebtListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DebtListFragment : Fragment() {

    private lateinit var binding: FragmentDebtListBinding

    private val viewModel: DebtsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDebtListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DebtListAdapter {

            this.findNavController().navigate(
                DebtListFragmentDirections.actionDebtListFragmentToNewDebtFragment(getString(
                    R.string.debt_details_title
                ), it.id)
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
            this.findNavController().navigate(
                DebtListFragmentDirections.actionDebtListFragmentToNewDebtFragment(
                    getString(R.string.add_fragment_title), -1
                )
            )
        }
    }
}