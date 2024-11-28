package contact_provider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import model.Contact

class AndroidContactProvider (val context:Context): ContactProviderApi {

    override fun getAllContacts() :List<Contact> {

        var result = listOf<Contact>()

       /* val requestPermissionLauncher = (context as ComponentActivity).registerForActivityResult(
            ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {*/
                // Разрешение уже есть, продолжаем
                result =  AndroidContactProvider(context).fetchContact()
          /*  } else {
                // Разрешение не предоставлено
            }

        }*/
        // Проверка разрешения на доступ к контактам
        if(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED ) {

                // Разрешение уже есть, продолжаем
                result = AndroidContactProvider(context).fetchContact()
            } else{
                // Запрашиваем разрешение
              //  requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)

            }

        return result
    }
     private fun fetchContact(): List<Contact> {
        val contactList =
            mutableMapOf<String, Contact>()  // Используем Map для уникальных контактов
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,  // Используем CONTACT_ID для уникальности
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            null,
            null,
            null
        )

        cursor?.use {
            val contactIdIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            while (cursor.moveToNext()) {
                val contactId = cursor.getString(contactIdIndex)
                val name = cursor.getString(nameIndex)
                val number = cursor.getString(numberIndex)

                // Проверяем, существует ли уже контакт с таким же ID
                if (contactList.containsKey(contactId)) {
                    // Если существует, обновляем его (например, добавляем новый номер)
                    val existingContact = contactList[contactId]
                    existingContact?.let {
                        // Добавляем новый номер (можно здесь реализовать логику объединения)
                        it.phoneNumber =
                            "${it.phoneNumber}, $number"  // Пример объединения номеров
                    }
                } else {
                    // Если контакта нет, добавляем новый
                    contactList[contactId] = Contact(contactId, name, number)
                }
            }
        }

        return contactList.values.toList()  // Преобразуем Map в List
    }
}



