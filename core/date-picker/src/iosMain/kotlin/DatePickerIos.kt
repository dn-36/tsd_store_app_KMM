import platform.Foundation.NSDateFormatter
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleCancel
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleActionSheet
import platform.UIKit.UIDatePicker
import platform.UIKit.UIDatePickerMode
import platform.UIKit.UIViewController

class DatePickerIos ( private val viewController: UIViewController ) : DatePickerApi {

        override fun showDatePicker(onDateSelected: (String) -> Unit) {
            // Создаем UIAlertController для отображения выбора даты
            val alertController = UIAlertController.alertControllerWithTitle(
                "Выберите дату",
                null,
                UIAlertControllerStyleActionSheet
            )

            // Создаем UIDatePicker
            val datePicker = UIDatePicker()
            datePicker.datePickerMode = UIDatePickerMode.UIDatePickerModeDate

            // Добавляем UIDatePicker в UIAlertController
            alertController.view.addSubview(datePicker)

            // Добавляем кнопку "Выбрать"
            val selectAction = UIAlertAction.actionWithTitle(
                "Выбрать",
                UIAlertActionStyleDefault
            ) { _ ->
                val formatter = NSDateFormatter()
                formatter.dateFormat = "yyyy-MM-dd"
                val selectedDate = formatter.stringFromDate(datePicker.date)
                onDateSelected(selectedDate)
            }
            alertController.addAction(selectAction)

            // Добавляем кнопку "Отмена"
            val cancelAction = UIAlertAction.actionWithTitle(
                "Отмена",
                UIAlertActionStyleCancel,
                null
            )
            alertController.addAction(cancelAction)

            // Показываем UIAlertController
            viewController.presentViewController(
                alertController,
                animated = true,
                completion = null
            )
        }
}