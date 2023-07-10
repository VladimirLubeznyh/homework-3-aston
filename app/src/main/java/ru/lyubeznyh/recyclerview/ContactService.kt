package ru.lyubeznyh.recyclerview

import androidx.lifecycle.MutableLiveData

class ContactService() {

    val contactsLiveData: MutableLiveData<MutableList<Contact>> =
        MutableLiveData(generateContacts())

    private val isCheckedSetId: HashSet<Int> = hashSetOf()

    fun onCheckContact(contact: Contact, isCheced: Boolean) {
        if (contact.isChecked == isCheced) return
        else {
            val nContact = contact.copy(isChecked = isCheced)
            if (isCheced) isCheckedSetId.add(nContact.id) else isCheckedSetId.remove(nContact.id)
            replaceContact(nContact)
        }
    }

    fun addContact(contact: Contact) {
        val list = contactsLiveData.value
        list?.add(0, contact)
        contactsLiveData.value = list
    }

    fun replaceContact(newContact: Contact) {
        val list = contactsLiveData.value
        val indexContact = list?.indexOfFirst {
            it.id == newContact.id
        }

        if (indexContact == -1) return
        indexContact?.let {
            list.removeAt(it)
            list.add(it, newContact)
        }
        contactsLiveData.value = list
    }

    fun deleteContact(position: Int) {
        val list = contactsLiveData.value
        list?.removeAt(position)
        contactsLiveData.value = list
    }

    fun deleteAllChecked() {
        val list = contactsLiveData.value
        list?.removeIf { isCheckedSetId.contains(it.id) } ?: return
        isCheckedSetId.clear()
        contactsLiveData.value = list
    }

    fun moveContact(fromPosition: Int, toPosition: Int) {
        val list = contactsLiveData.value
        list?.swap(fromPosition, toPosition)
        contactsLiveData.value = list
    }
}
