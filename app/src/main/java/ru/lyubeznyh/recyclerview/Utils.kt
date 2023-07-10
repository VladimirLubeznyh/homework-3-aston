package ru.lyubeznyh.recyclerview

import com.github.javafaker.Faker

var globalIdContact: Int = 0
    get() {
        field++
        return field
    }
    private set

fun generateContacts(): MutableList<Contact> {
    val contactsList: MutableList<Contact> = mutableListOf()

    repeat(100) {
        contactsList.add(createContact(globalIdContact))
    }
    return contactsList
}

fun createContact(id: Int): Contact {
    val faker = Faker()
    return Contact(
        id = id,
        firstName = faker.name().firstName(),
        lastName = faker.name().lastName(),
        phoneNumber = faker.phoneNumber().phoneNumber().replace('.', '-'),
        isChecked = false
    )
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}
