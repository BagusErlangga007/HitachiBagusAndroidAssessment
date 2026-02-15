package com.example.hitachibagusassessment.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hitachibagusassessment.R
import com.example.hitachibagusassessment.databinding.ItemUserBinding
import com.example.hitachibagusassessment.domain.model.User

class UserAdapter(
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val items = mutableListOf<User>()

    fun submitList(data: List<User>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(binding) {
            tvUsername.text = user.username

            Glide.with(ivAvatarUserList.context)
                .load(user.avatarUrl)
                .placeholder(R.drawable.img_android)
                .error(R.drawable.img_android)
                .circleCrop()
                .into(ivAvatarUserList)

            root.setOnClickListener {
                onItemClick(user)
            }
        }
    }
}
