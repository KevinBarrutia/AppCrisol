package pe.edu.crisol.libreria.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        val bottomNav = binding.bottomNav

        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener {
                controller, destination, arguments ->
            when(destination.id) {
                R.id.homeFragment -> bottomNav.visibility = View.VISIBLE
                R.id.profileFragment -> bottomNav.visibility = View.VISIBLE
                R.id.shoppingCartFragment -> bottomNav.visibility = View.VISIBLE
                R.id.moreFragment -> bottomNav.visibility = View.VISIBLE
                R.id.searchFragment -> bottomNav.visibility = View.VISIBLE
                else -> bottomNav.visibility = View.GONE
            }
        }

        val badge = bottomNav.getOrCreateBadge(R.id.shoppingCartFragment)
        badge.isVisible =true
        badge.number = 99
    }

}