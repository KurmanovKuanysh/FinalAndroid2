package kz.sd.shop.adapters

import kz.sd.shop.models.Product
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import kz.sd.shop.databinding.ItemProductBinding

// Адаптер для списка продуктов
class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffUtils()) {

    // Объявление переменной для обработки нажатий на элементы списка
    var itemClick: ((Product) -> Unit)? = null

    // Внутренний класс для определения способа сравнения элементов списка
    class ProductDiffUtils : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            // Проверка на идентичность продуктов
            return oldItem.asin == newItem.asin
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            // Проверка на идентичность содержимого продуктов
            return oldItem == newItem
        }
    }

    // Метод для создания нового ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Создание ViewHolder с использованием привязки данных
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // Метод для привязки данных к ViewHolder
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    // Внутренний класс ViewHolder для отображения элемента списка
    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        // Метод для привязки данных к элементам интерфейса
        fun bindView(item: Product) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.product_photo)
                    .into(productImage)
                productTitle.text = item.product_title
                productPrice.text = item.product_price
                productRating.text = "Rating: ${item.product_star_rating} (${item.product_num_ratings} Reviews)"
                productDelivery.text = item.delivery

                // Установка обработчика нажатия на элемент списка
                itemView.setOnClickListener {
                    itemClick?.invoke(item)
                }
            }
        }
    }
}
