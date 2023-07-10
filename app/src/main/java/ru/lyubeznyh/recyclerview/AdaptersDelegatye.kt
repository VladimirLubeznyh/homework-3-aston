package ru.lyubeznyh.recyclerview

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.lyubeznyh.recyclerview.databinding.ItemContactBinding

fun contactAdapterDelegate(
    click: (Contact) -> Unit,
    onChecked: (Contact, Boolean) -> Unit
) = adapterDelegateViewBinding<Contact, Contact, ItemContactBinding>(
    { layoutInflater, parent -> ItemContactBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        with(binding) {
            tvFirstName.text = item.firstName
            tvLastName.text = item.lastName
            tvPhoneNumber.text = item.phoneNumber
            cbIsChecked.isChecked = item.isChecked
        }
    }
    binding.root.setOnClickListener {
        click.invoke(item)
    }

    binding.cbIsChecked.setOnCheckedChangeListener { _, b ->
        onChecked.invoke(item, b)
    }
}
