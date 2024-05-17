package kz.sd.shop

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.shop.databinding.ActivityMainBinding
import kz.sd.shop.utilities.BottomNavigationViewListener

// Главная активность приложения
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationViewListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    // Создание активности
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Настройка контента активности с использованием привязки данных
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение NavHostFragment и контроллера навигации
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController

        // Настройка нижней панели навигации
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    // Метод для отображения или скрытия нижней панели навигации
    override fun showBottomNavigationView(show: Boolean) {
        if (show) {
            binding.bottomNavigation.visibility = View.VISIBLE
        } else {
            binding.bottomNavigation.visibility = View.GONE
        }
    }
}
