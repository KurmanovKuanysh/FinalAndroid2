package kz.sd.shop.fragments

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentProductDetailsBinding
import kz.sd.shop.models.Product

// Фрагмент для отображения деталей продукта, наследуется от BaseFragment с использованием FragmentProductDetailsBinding
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>(FragmentProductDetailsBinding::inflate) {
    // Получение аргументов, переданных в фрагмент, используя navArgs
    private val args: ProductDetailsFragmentArgs by navArgs()

    // Переопределение метода onViewCreated для настройки вида после его создания
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение выбранного продукта из аргументов
        val product = args.product

        // Настройка элементов интерфейса с использованием привязки данных
        with(binding) {
            // Установка обработчика нажатия на кнопку "Назад"
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            // Загрузка изображения продукта с помощью библиотеки Glide
            Glide.with(requireContext())
                .load(product.product_photo)
                .into(productImage)

            // Установка заголовка продукта
            productTitle.text = product.product_title

            // Установка цены продукта с учетом валюты
            productPrice.text = "${product.product_price} ${product.currency}"

            // Установка рейтинга продукта и количества отзывов
            productRating.text = "Rating: ${product.product_star_rating} (${product.product_num_ratings} Reviews)"

            // Установка описания продукта (в данной реализации описание отсутствует)
            productDescription.text = "Description not available"

            // Установка обработчика нажатия на кнопку "Добавить в корзину"
            addToCartButton.setOnClickListener {
                // Действие добавления продукта в корзину (пока не реализовано)
            }
        }
    }
}
