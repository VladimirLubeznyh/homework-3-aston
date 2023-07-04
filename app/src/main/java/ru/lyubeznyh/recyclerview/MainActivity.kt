package ru.lyubeznyh.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.lyubeznyh.recyclerview.databinding.ActivityMainBinding
import ru.lyubeznyh.recyclerview.databinding.DialogAddContactBinding

class MainActivity : AppCompatActivity() {
    private var currentContact: Contact? = null

    private val contactService = ContactService()

    private lateinit var binding: ActivityMainBinding
    private lateinit var bidingDialog: DialogAddContactBinding

    private val addContactDialog: AlertDialog by lazy {
        AlertDialog.Builder(this)
            .setView(bidingDialog.root)
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                with(bidingDialog) {
                    val contact = Contact(
                        currentContact?.id ?: globalIdContact,
                        actvFirstName.text.toString(),
                        actvLastName.text.toString(),
                        actvPhoneNumber.text.toString(),
                        false
                    )
                    currentContact?.let { contactService.replaceContact(contact) }
                        ?: contactService.addContact(contact)
                }
            }
            .setNegativeButton(getString(R.string.cancel)) { i1, _ ->
                currentContact = null
                i1.cancel()
            }
            .create()
    }
    private val onItemSwipedToDelete = { position: Int ->
        contactService.deleteContact(position)
    }
    private val onItemMove = { fromPosition: Int, toPosition: Int ->
        contactService.moveContact(fromPosition, toPosition)
    }

    private val adapter: AsyncListDifferDelegationAdapter<Contact> by lazy {
        AsyncListDifferDelegationAdapter(
            ContactsDiffCallback(),
            AdapterDelegatesManager(
                contactAdapterDelegate(
                    { contact ->
                        currentContact = contact
                        showDialogReplace(contact)
                    },
                    { contact, b ->
                        contactService.onCheckContact(contact, b)
                    })
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bidingDialog = DialogAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvContacts.adapter = adapter


        binding.rvContacts.addItemDecoration(ContactItemDecorator(20))

        ItemTouchHelper(
            SwipeAndDragContacts(
                onItemMove,
                onItemSwipedToDelete
            )
        ).attachToRecyclerView(
            binding.rvContacts
        )
        val itemAnimator = binding.rvContacts.itemAnimator
        if (itemAnimator is DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }
        binding.ivAdd.setOnClickListener {
            showDialog()
        }
        contactService.contactsLiveData.observe(this) {
            adapter.items = it.toList()
        }
        binding.ivDeleteAll.setOnClickListener {
            contactService.deleteAllChecked()
        }
    }

    private fun showDialog() {
        if (addContactDialog.isShowing) return
        bidingDialog.actvLastName.text = null
        bidingDialog.actvFirstName.text = null
        bidingDialog.actvPhoneNumber.text = null
        addContactDialog.show()

    }

    private fun showDialogReplace(contact: Contact) {
        if (addContactDialog.isShowing) return
        bidingDialog.actvLastName.setText(contact.lastName)
        bidingDialog.actvFirstName.setText(contact.firstName)
        bidingDialog.actvPhoneNumber.setText(contact.phoneNumber)
        addContactDialog.show()
    }
}
