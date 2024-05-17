package kz.sd.shop.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentChoiceBinding

// Фрагмент для выбора способа оплаты, наследуется от BaseFragment с использованием FragmentChoiceBinding
class ChoiceFragment : BaseFragment<FragmentChoiceBinding>(FragmentChoiceBinding::inflate) {

    // Получение аргументов, переданных в фрагмент, используя navArgs
    private val args: ChoiceFragmentArgs by navArgs()

    // Переопределение метода onBindView для настройки вида
    override fun onBindView() {
        super.onBindView()

        // Использование binding для доступа к элементам интерфейса и установки обработчиков нажатий
        with(binding) {
            // Установка обработчика нажатий для кнопки бонусов
            bonus.setOnClickListener {
                // Навигация к BonusPaymentFragment с передачей аргумента total
                findNavController().navigate(ChoiceFragmentDirections.actionChoiceFragmentToBonusPaymentFragment(args.total))
            }
            // Установка обработчика нажатий для кнопки карты
            card.setOnClickListener {
                // Навигация к PaymentFragment с передачей аргумента total
                findNavController().navigate(ChoiceFragmentDirections.actionChoiceFragmentToPaymentFragment(args.total))
            }
        }
    }
}
