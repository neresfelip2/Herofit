package br.com.neresfelip.herofitbrasil.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.neresfelip.herofitbrasil.R
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.TriangleAlert

@Composable
fun ErrorComponent(message: String) {

    val errorColor = Color.Red

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            Lucide.TriangleAlert,
            contentDescription = stringResource(R.string.acc_error_icon),
            Modifier.size(96.dp),
            tint = errorColor
        )
        Spacer(Modifier.height(_root_ide_package_.br.com.neresfelip.herofitbrasil.presentation.ui.theme.defaultPadding))
        Text(
            message,
            color = errorColor
        )
    }

}

@Preview(showSystemUi = true)
@Composable
private fun ErrorComponentPreview() {
    ErrorComponent("Algo deu errado")
}