package kz.sd.shop.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kz.sd.shop.models.Product

// Абстрактный базовый класс ViewHolder с использованием view binding и обобщенного типа
abstract class BaseViewHolder<VB : ViewBinding, T>(protected open val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    // Абстрактный метод для привязки данных к виду, должен быть реализован в подклассах
    abstract fun bindView(item: T)
}

// Абстрактный класс для ViewHolder, специфичный для продуктов, наследующий BaseViewHolder
abstract class BaseProductViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Product>(binding)
