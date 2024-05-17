package kz.sd.shop.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.adapters.ProductAdapter
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentProductsBinding
import kz.sd.shop.network.AmazonViewModel

// Аннотация для интеграции с Dagger Hilt, чтобы обеспечить внедрение зависимостей
@AndroidEntryPoint
// Фрагмент для отображения списка продуктов, наследуется от BaseFragment с использованием FragmentProductsBinding
class ProductsFragment : BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate) {
    // Создание ViewModel с помощью Hilt
    private val viewModel: AmazonViewModel by viewModels()

    // Переопределение метода onBindView для настройки вида
    override fun onBindView() {
        super.onBindView()

        // Инициализация поиска продуктов с запросом "Laptop"
        viewModel.searchProducts("Laptop")

        // Создание экземпляра адаптера для RecyclerView
        val adapter1 = ProductAdapter()

        // Наблюдение за изменениями в amazonLiveData и обновление списка продуктов в адаптере
        viewModel.amazonLiveDate.observe(viewLifecycleOwner) {
            adapter1.submitList(it?.data?.products)
        }

        // Назначение адаптера для RecyclerView
        binding.recycler1.adapter = adapter1
        // Установка компоновщика для RecyclerView в виде вертикального списка
        binding.recycler1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Установка обработчика нажатий на элементы списка
        adapter1.itemClick = {
            // Навигация к фрагменту с деталями продукта, передача выбранного продукта в качестве аргумента
            findNavController().navigate(
                ProductsFragmentDirections.actionProductsFragment2ToProductDetailsFragment(it)
            )
        }
    }
}
