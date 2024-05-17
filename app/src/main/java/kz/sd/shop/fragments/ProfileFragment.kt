package kz.sd.shop.fragments

import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.base.BaseFragment
import kz.sd.shop.databinding.FragmentProfileBinding

// Фрагмент для отображения профиля пользователя, наследуется от BaseFragment с использованием FragmentProfileBinding
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    // В этом месте можно добавлять методы и функциональность для этого фрагмента
}
