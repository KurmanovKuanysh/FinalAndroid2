package kz.sd.shop

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

// Инструментированный тест, выполняемый на устройстве Android
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    // Тестирование контекста приложения
    @Test
    fun useAppContext() {
        // Получение контекста приложения
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // Проверка соответствия пакетного имени приложения
        assertEquals("kz.sd.shop", appContext.packageName)
    }
}
