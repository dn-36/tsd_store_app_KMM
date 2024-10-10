package contact_provider

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Contact
import platform.Contacts.CNAuthorizationStatusAuthorized
import platform.Contacts.CNAuthorizationStatusNotDetermined
import platform.Contacts.CNContactFetchRequest
import platform.Contacts.CNContactFormatter
import platform.Contacts.CNContactFormatterStyle
import platform.Contacts.CNContactPhoneNumbersKey
import platform.Contacts.CNContactStore
import platform.Contacts.CNEntityType
import platform.Contacts.CNLabeledValue
import platform.Contacts.CNPhoneNumber
import platform.Foundation.NSError
import platform.Foundation.NSLog
import platform.Foundation.*
import platform.darwin.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class IOSContactProvider : ContactProviderApi {

    override fun getAllContacts(): List<Contact> {
        val contactList = mutableListOf<Contact>()
        val contactStore = CNContactStore()

        // Проверка статуса доступа к контактам
        val authorizationStatus = CNContactStore.authorizationStatusForEntityType(CNEntityType.CNEntityTypeContacts)

        runBlocking {
            if (authorizationStatus == CNAuthorizationStatusNotDetermined) {
                // Если статус не определён, запрашиваем разрешение и ожидаем его результат
                requestContactsAccess(contactStore)
            }
        }

        if (authorizationStatus == CNAuthorizationStatusAuthorized || CNContactStore.authorizationStatusForEntityType(CNEntityType.CNEntityTypeContacts) == CNAuthorizationStatusAuthorized) {
            // Если разрешение уже есть или было предоставлено, запрашиваем контакты
            fetchContacts(contactStore, contactList)
        } else {
            NSLog("Access to contacts is restricted or denied.")
        }

        return contactList
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun fetchContacts(contactStore: CNContactStore, contactList: MutableList<Contact>) {
        val keysToFetch = listOf(
            CNContactFormatter.descriptorForRequiredKeysForStyle(CNContactFormatterStyle.CNContactFormatterStyleFullName),
            CNContactPhoneNumbersKey
        )

        val fetchRequest = CNContactFetchRequest(keysToFetch)

        memScoped {
            val errorPtr = alloc<ObjCObjectVar<NSError?>>()

            val success = contactStore.enumerateContactsWithFetchRequest(fetchRequest, errorPtr.ptr) { cnContact, _ ->
                cnContact?.let {
                    val name = CNContactFormatter.stringFromContact(it,
                        CNContactFormatterStyle.CNContactFormatterStyleFullName
                    ) ?: "No Name"

                    // Получение номеров телефона без использования приведения через value
                    val phoneNumbers = it.phoneNumbers.mapNotNull { labeledValue ->
                        (labeledValue as? CNLabeledValue)?.let { labeled ->
                            labeled.value as? CNPhoneNumber
                        }?.stringValue
                    }

                    if (phoneNumbers.isNotEmpty()) {
                        val contact = Contact(
                            id = it.identifier,
                            name = name,
                            phoneNumber = phoneNumbers.joinToString(", ")
                        )
                        contactList.add(contact)
                    }
                }
            }

            val error = errorPtr.value
            if (error != null) {
                NSLog("Error fetching contacts: ${error.localizedDescription}")
            } else if (!success) {
                NSLog("Failed to enumerate contacts for an unknown reason")
            }
        }
    }

    private suspend fun requestContactsAccess(contactStore: CNContactStore) = suspendCoroutine<Unit> { continuation ->
        contactStore.requestAccessForEntityType(CNEntityType.CNEntityTypeContacts) { granted, error ->
            if (granted) {
                NSLog("Access to contacts granted.")
            } else {
                NSLog("Access to contacts was denied.")
            }
            continuation.resume(Unit)  // Резюмируем корутину после завершения запроса
        }
    }
}
/*class IOSContactProvider : ContactProviderApi {

    override fun getAllContacts(): List<Contact> {

            val contactList = mutableListOf<Contact>()

            val contactStore = CNContactStore()

            // Проверка статуса доступа к контактам
            val authorizationStatus =
                CNContactStore.authorizationStatusForEntityType(CNEntityType.CNEntityTypeContacts)

            if (authorizationStatus == CNAuthorizationStatusNotDetermined) {
                // Если статус не определён, запрашиваем разрешение
                contactStore.requestAccessForEntityType(CNEntityType.CNEntityTypeContacts) { granted, error ->
                    if (granted) {
                        // Если разрешение предоставлено, запрашиваем контакты

                        fetchContacts(contactStore, contactList)
                    } else {
                        NSLog("Access to contacts was denied.")
                    }
                }
            } else if (authorizationStatus == CNAuthorizationStatusAuthorized) {
                // Если разрешение уже есть, запрашиваем контакты
                fetchContacts(contactStore, contactList)
            } else {
                NSLog("Access to contacts is restricted or denied.")
            }

            return contactList

    }

    @OptIn(ExperimentalForeignApi::class)
    private fun fetchContacts(contactStore: CNContactStore, contactList: MutableList<Contact>) {
        val keysToFetch = listOf(
            CNContactFormatter.descriptorForRequiredKeysForStyle(CNContactFormatterStyle.CNContactFormatterStyleFullName),
            CNContactPhoneNumbersKey
        )

        val fetchRequest = CNContactFetchRequest(keysToFetch)

        memScoped {
            val errorPtr = alloc<ObjCObjectVar<NSError?>>()

            val success = contactStore.enumerateContactsWithFetchRequest(fetchRequest, errorPtr.ptr) { cnContact, _ ->
                cnContact?.let {
                    val name = CNContactFormatter.stringFromContact(it,
                        CNContactFormatterStyle.CNContactFormatterStyleFullName
                    ) ?: "No Name"

                    // Получение номеров телефона без использования приведения через value
                    val phoneNumbers = it.phoneNumbers.mapNotNull { labeledValue ->
                        (labeledValue as? CNLabeledValue)?.let { labeled ->
                            labeled.value as? CNPhoneNumber
                        }?.stringValue
                    }

                    if (phoneNumbers.isNotEmpty()) {
                        val contact = Contact(
                            id = it.identifier,
                            name = name,
                            phoneNumber = phoneNumbers.joinToString(", ")
                        )
                        contactList.add(contact)
                    }
                }
            }

            val error = errorPtr.value
            if (error != null) {
                NSLog("Error fetching contacts: ${error.localizedDescription}")
            } else if (!success) {
                NSLog("Failed to enumerate contacts for an unknown reason")
            }
        }
    }
}*/
