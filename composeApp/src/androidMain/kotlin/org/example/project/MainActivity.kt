package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import contact_provider.ContactProviderApi
import io.ktor.http.parametersOf
import org.example.project.app.ui.App
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    companion object{
        lateinit var context: MainActivity
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this
        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }


       /*initKoin {
            androidContext(this@MainActivity.applicationContext)
        }*/
        setContent {
            App.Content()
          //  chatScreen.ChatsScreen().Content()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App.Content()
}

