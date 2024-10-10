package contact_provider

import model.Contact

interface ContactProviderApi {
    fun getAllContacts(): List<Contact>
}