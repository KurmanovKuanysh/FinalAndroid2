package kz.sd.shop.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.airbnb.lottie.LottieAnimationView
import kz.sd.shop.R
import kz.sd.shop.utilities.BottomNavigationViewListener
import java.lang.Exception
import java.lang.RuntimeException

// Псевдоним для функции, которая раздувает view binding
typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

// Абстрактный базовый класс фрагмента с использованием view binding
abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    // Приватная переменная для хранения экземпляра binding
    private var _binding: VB? = null

    // Переменная для слушателя нижней навигации
    private lateinit var bottomNavigationViewListener: BottomNavigationViewListener
    // Открытая переменная для отображения или скрытия нижней навигации, может быть переопределена в подклассах
    open var showBottomNavigation: Boolean = true

    // Публичное свойство для получения экземпляра binding, выбрасывает исключение, если значение null
    val binding get() = _binding ?: throw RuntimeException()

    // Вызывается для раздувания (inflating) вида фрагмента
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Раздуваем layout с использованием переданной функции inflate и сохраняем в _binding
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    // Вызывается после создания вида
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            // Показать или скрыть нижнюю навигацию в зависимости от showBottomNavigation
            bottomNavigationViewListener.showBottomNavigationView(showBottomNavigation)
            // Вызов методов, которые можно переопределить в подклассах
            onInit()
            onBindView()
            bindViewModel()
        } catch (e: Exception) {
            // Логирование исключений при привязке вида
            Log.e("OnViewCreated", "Exception by view binding: ${e.message}")
        }
    }

    // Вызывается при присоединении фрагмента к контексту
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Проверка, что контекст реализует BottomNavigationViewListener
        if (context is BottomNavigationViewListener) {
            bottomNavigationViewListener = context
        } else {
            // Выбрасывание исключения, если контекст не реализует BottomNavigationViewListener
            throw RuntimeException("$context, error")
        }
    }

    // Метод для показа кастомного диалога успеха
    protected fun showCustomDialog(title: String, content: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_success_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val animationView: LottieAnimationView = dialog.findViewById(R.id.animation)
        val titleTextView: TextView = dialog.findViewById(R.id.title)
        val contentTextView: TextView = dialog.findViewById(R.id.content)

        animationView.playAnimation()
        titleTextView.text = title
        contentTextView.text = content
        dialog.show()
        val button: Button = dialog.findViewById(R.id.ok_btn)
        button.setOnClickListener {
            dialog.dismiss()
        }
    }

    // Метод для показа кастомного диалога отмены
    protected fun showCustomCancelDialog(title: String, content: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_cancel_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val animationView: LottieAnimationView = dialog.findViewById(R.id.cancel_animation)
        val titleTextView: TextView = dialog.findViewById(R.id.cancel_title)
        val contentTextView: TextView = dialog.findViewById(R.id.cancel_content)

        animationView.playAnimation()
        titleTextView.text = title
        contentTextView.text = content
        dialog.show()
        val button: Button = dialog.findViewById(R.id.cancel_ok_btn)
        button.setOnClickListener {
            dialog.dismiss()
        }
    }

    // Методы, которые можно переопределить в подклассах для инициализации, привязки вида и привязки ViewModel
    open fun onInit() {}
    open fun onBindView() {}
    open fun bindViewModel() {}

    // Вызывается при уничтожении вида, освобождает binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
