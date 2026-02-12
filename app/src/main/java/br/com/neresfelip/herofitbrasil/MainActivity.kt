package br.com.neresfelip.herofitbrasil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.neresfelip.herofitbrasil.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            _root_ide_package_.br.com.neresfelip.herofitbrasil.presentation.ui.theme.HerofitBrasilTheme {
                AppNavigation()
            }
        }
    }
}