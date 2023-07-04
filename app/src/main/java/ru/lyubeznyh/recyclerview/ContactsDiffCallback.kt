package ru.lyubeznyh.recyclerview

import androidx.recyclerview.widget.DiffUtil

class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
        oldItem == newItem
}
