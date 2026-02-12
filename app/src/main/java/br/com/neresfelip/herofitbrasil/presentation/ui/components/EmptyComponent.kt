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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.neresfelip.herofitbrasil.R

@Composable
fun EmptyComponent() {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_deflated_ball),
            contentDescription = stringResource(R.string.acc_empty_content),
            Modifier.size(256.dp),
        )
        Spacer(Modifier.height(_root_ide_package_.br.com.neresfelip.herofitbrasil.presentation.ui.theme.defaultPadding))
        Text(
            "Nenhum esporte encontrado",
        )
    }

}

@Preview(showSystemUi = true)
@Composable
private fun EmptyComponentPreview() {
    EmptyComponent()
}