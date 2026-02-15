package com.example.hitachibagusassessment.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hitachibagusassessment.R
import com.example.hitachibagusassessment.databinding.FragmentSearchUserBinding
import com.example.hitachibagusassessment.domain.model.UiState
import com.example.hitachibagusassessment.ui.ext.gone
import com.example.hitachibagusassessment.ui.ext.visible
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search_user) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchUserBinding
    private lateinit var adapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSearchUserBinding.bind(view)

        adapter = UserAdapter { user ->
            val action =
                SearchFragmentDirections
                    .actionSearchUserFragmentToUserDetailFragment(
                        user.username
                    )
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            viewModel.search(query)
        }

        observeState()
        handleSubmit()
    }

    private fun observeState() {

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progress.visible()
                    binding.recyclerView.gone()
                    binding.errorText.gone()
                }

                is UiState.Success -> {
                    Log.d("SearchFragment", "Data size: ${state.data.size}") // <-- debug

                    binding.progress.gone()
                    binding.recyclerView.visible()
                    binding.errorText.gone()
                    adapter.submitList(state.data)
                }

                is UiState.Error -> {
                    binding.progress.gone()
                    binding.recyclerView.gone()
                    binding.errorText.visible()
                    binding.errorText.text = state.message
                }
            }
        }
    }
    fun handleSubmit()
    {

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotBlank()) {
                viewModel.search(query)
            }
        }
    }

}
