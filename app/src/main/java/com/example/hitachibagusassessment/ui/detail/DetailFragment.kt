package com.example.hitachibagusassessment.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
//import com.bumptech.glide.Glide
import com.example.hitachibagusassessment.R
import com.example.hitachibagusassessment.databinding.FragmentDetailBinding
import com.example.hitachibagusassessment.domain.model.UiState
import com.example.hitachibagusassessment.domain.model.UserDetail
import com.example.hitachibagusassessment.ui.ext.gone
import com.example.hitachibagusassessment.ui.ext.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: UserDetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    lateinit var _binding : FragmentDetailBinding
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding  = FragmentDetailBinding.bind(view)


        val progress = binding.progress
        val content =binding.content
        val error = binding.errorText



        Log.d("DETAIL", "Fragment opened, username=${args.username}")
        viewModel.loadUser(args.username)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    progress.visible()
                    content.gone()
                    error.gone()
                }
                is UiState.Success -> {
                    progress.gone()
                    content.visible()
                    error.gone()
                    handleDetail(state.data)
                }
                is UiState.Error -> {
                    progress.gone()
                    content.gone()
                    error.visible()
                    error.text = state.message
                }
            }
        }
    }
    private fun handleDetail(userDetail: UserDetail)
    {
            binding.tvUserName.text = "user name : ${userDetail.username ?: "-"}"
            binding.tvFollowers.text = "Followers: ${userDetail.followers}"
            binding.tvFollowing.text = "Following: ${userDetail.following}"

            Glide.with(this)
                .load(userDetail.avatarUrl)
                .into(binding.ivAvatar)

    }
}