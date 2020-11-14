package com.jay.randomuser.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jay.randomuser.databinding.ItemUserBinding
import com.jay.randomuser.view.base.BaseRecyclerViewAdapter
import com.jay.randomuser.view.main.model.UserUiModel

class MainAdapter : BaseRecyclerViewAdapter<UserUiModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UserUiModel> {
        return ItemViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ItemViewHolder(
        private val binding: ItemUserBinding
    ) : BaseViewHolder<UserUiModel>(binding = binding) {

        override fun bind(item: UserUiModel) {
            binding.root.setOnClickListener {
                item.onclick.invoke()
            }
        }

        override fun recycle() {
            Glide.with(binding.ivProfile)
                .clear(binding.ivProfile)
        }
    }
}