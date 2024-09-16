package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.myapp.navView.CreateScreen
import com.example.myapp.navView.ReadScreen
import com.example.myapp.roomDB.PessoaDataBase
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.viewModel.PessoaViewModel
import com.example.myapp.viewModel.Repository

class MainActivity : ComponentActivity() {
    private val bd by lazy{
        Room.databaseBuilder(
            applicationContext,
            PessoaDataBase::class.java,
            "pessoa.db"
        ).build()
    }

    private val viewModel by viewModels<PessoaViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class<T>): T{
                    return PessoaViewModel(Repository(bd)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "create") {
                    composable("create") {
                        CreateScreen(navController, viewModel)
                    }
                    composable("read") {
                        ReadScreen(navController, viewModel)
                    }
                }
            }
        }
    }
}
