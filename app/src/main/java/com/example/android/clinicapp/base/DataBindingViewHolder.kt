package com.example.android.clinicapp.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.R
import androidx.recyclerview.widget.RecyclerView


class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(R.id.dataBinding,item)
        binding.executePendingBindings()
    }
}
