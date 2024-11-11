import android.content.Context
import android.app.DatePickerDialog
import androidx.activity.ComponentActivity
import java.util.*

class DatePickerAndroid () : DatePickerApi {

        override fun showDatePicker(onDateSelected: (String) -> Unit) {
            // Получаем текущую дату
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val context = ContextDatePicker.context as? Context
            if (context != null) {
                // Создаем DatePickerDialog
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        // Форматируем дату в строку
                        val formattedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                        onDateSelected(formattedDate)
                    },
                    year,
                    month,
                    day
                )

                // Показываем диалог
                datePickerDialog.show()
            }
        }

}