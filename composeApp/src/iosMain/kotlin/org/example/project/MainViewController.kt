package org.example.project

import DatePickerIos
import androidx.compose.ui.window.ComposeUIViewController
import com.project.network.ConstData
import org.example.project.app.ui.App
import platform.UIKit.UIApplication
import platform.UIKit.UINavigationController
import platform.UIKit.UITabBarController
import platform.UIKit.UIViewController

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App.Content()

    ContextDatePicker.context = getCurrentViewController()?:ComposeUIViewController{}
}


 fun getCurrentViewController(): UIViewController? {
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
    return getVisibleViewController(rootViewController)
}

private fun getVisibleViewController(viewController: UIViewController?): UIViewController? {
    return when (viewController) {
        is UINavigationController -> getVisibleViewController(viewController.visibleViewController)
        is UITabBarController -> getVisibleViewController(viewController.selectedViewController)
        else -> viewController?.presentedViewController ?: viewController
    }
}