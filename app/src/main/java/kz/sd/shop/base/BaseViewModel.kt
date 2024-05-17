package kz.sd.shop.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// Абстрактный базовый класс ViewModel для работы с корутинами и управлением состоянием загрузки и ошибок
abstract class BaseViewModel : ViewModel() {
    // Создание CoroutineScope с использованием IO диспетчера и нового Job
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    // Приватное MutableLiveData для управления состоянием загрузки
    private var _loadingLiveData = MutableLiveData<Boolean>()
    // Публичное LiveData для наблюдения за состоянием загрузки
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    // Приватное MutableLiveData для управления исключениями
    private var _exceptionLiveData = MutableLiveData<String?>()
    // Публичное LiveData для наблюдения за исключениями
    val exceptionLiveData: LiveData<String?> = _exceptionLiveData

    // Функция для запуска корутин с управлением состоянием загрузки и обработкой ошибок
    fun <T> launch(
        request: suspend () -> T, // Запрос, выполняемый в корутине
        onSuccess: (T) -> Unit = { } // Обработчик успешного завершения запроса
    ) {
        coroutineScope.launch {
            try {
                // Установка состояния загрузки в true перед выполнением запроса
                _loadingLiveData.postValue(true)
                // Выполнение запроса
                val response = request.invoke()
                // Вызов обработчика успешного завершения
                onSuccess.invoke(response)
            } catch (e: Exception) {
                // Обработка исключений и запись сообщения об ошибке в _exceptionLiveData
                _exceptionLiveData.postValue(e.message)
                // Логирование ошибки
                Log.e(">>>", e.message.orEmpty())
            } finally {
                // Установка состояния загрузки в false после завершения запроса
                _loadingLiveData.postValue(false)
            }
        }
    }
}
