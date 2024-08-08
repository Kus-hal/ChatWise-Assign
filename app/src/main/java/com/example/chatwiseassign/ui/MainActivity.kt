package com.example.chatwiseassign.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.chatwiseassign.R
import com.example.chatwiseassign.data.api.ProductApi
import com.example.chatwiseassign.data.repository.ProductRepository
import com.example.chatwiseassign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ProductViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelProviderFactory = ProductViewModelProviderFactory(ProductRepository())
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProductViewModel::class.java)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.productNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}