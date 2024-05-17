package kz.sd.shop.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kz.sd.shop.base.BaseViewModel
import kz.sd.shop.models.ApiResponse
import javax.inject.Inject

// ViewModel для взаимодействия с данными Amazon
@HiltViewModel
class AmazonViewModel @Inject constructor(
    private var repository: AmazonRepository
) : BaseViewModel() {

    // Живые данные для хранения ответа от API Amazon
    private var _amazonLiveDate = MutableLiveData<ApiResponse?>()
    var amazonLiveDate: LiveData<ApiResponse?> = _amazonLiveDate

    // Метод для поиска продуктов по запросу
    fun searchProducts(query: String) {
        launch(
            request = {
                repository.searchProducts(query)
            },
            onSuccess = {
                _amazonLiveDate.postValue(it)
            }
        )
    }

    // Метод для поиска ноутбуков
    fun searchLaptops() {
        launch(
            request = {
                repository.searchProducts("Laptop")
            },
            onSuccess = {
                _amazonLiveDate.postValue(it)
            }
        )
    }
}
