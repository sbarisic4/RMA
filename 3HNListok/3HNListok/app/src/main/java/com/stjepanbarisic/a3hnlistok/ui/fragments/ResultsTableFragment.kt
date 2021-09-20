package com.stjepanbarisic.a3hnlistok.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stjepanbarisic.a3hnlistok.databinding.FragmentResultsTableBinding
import com.stjepanbarisic.a3hnlistok.ui.adapters.FootballTeamAdapter
import com.stjepanbarisic.a3hnlistok.viewmodels.ResultsTableViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ResultsTableFragment : Fragment() {

    private val resultsTableViewModel: ResultsTableViewModel by viewModel()
    private val footballTeamsAdapter = FootballTeamAdapter(emptyList())
    private lateinit var resultsFragmentBinding: FragmentResultsTableBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resultsFragmentBinding = FragmentResultsTableBinding.inflate(inflater, container, false)
        return resultsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultsFragmentBinding.rvFootballTeamsList.apply {
            adapter = footballTeamsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultsTableViewModel.footballTeamsAsList.observe(this, { list ->
            if (!list.isNullOrEmpty()) {
                footballTeamsAdapter.loadNewTeams(list)
            }
        })
    }


}