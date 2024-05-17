package kz.sd.shop

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Класс приложения, использующий аннотацию HiltAndroidApp для настройки Dagger Hilt
@HiltAndroidApp
class MainApp : Application() {
}
