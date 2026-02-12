package br.com.neresfelip.herofitbrasil.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import br.com.neresfelip.herofitbrasil.R
import br.com.neresfelip.herofitbrasil.presentation.model.SportUI
import br.com.neresfelip.herofitbrasil.presentation.ui.UIState
import br.com.neresfelip.herofitbrasil.presentation.ui.components.EmptyComponent
import br.com.neresfelip.herofitbrasil.presentation.ui.components.ErrorComponent
import br.com.neresfelip.herofitbrasil.presentation.ui.components.LoadingComponent
import br.com.neresfelip.herofitbrasil.presentation.ui.theme.defaultPadding
import br.com.neresfelip.herofitbrasil.presentation.viewmodel.MainViewModel
import coil.compose.AsyncImage
import com.composables.icons.lucide.Forward
import com.composables.icons.lucide.LoaderCircle
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.TriangleAlert

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onClickItem: (String) -> Unit
) {

    val sportListState by viewModel.sportListState.collectAsState()

    Scaffold {
        Box(Modifier.padding(it)) {
            when (sportListState) {
                is UIState.Loading -> LoadingComponent()
                is UIState.Error -> ErrorComponent((sportListState as UIState.Error).description)
                is UIState.Success -> SuccessComponent(
                    (sportListState as UIState.Success<List<SportUI>>).data,
                    onClickItem
                )
            }
        }
    }
}

@Composable
private fun SuccessComponent(list: List<SportUI>, onClickItem: (String) -> Unit) {
    if (list.isEmpty()) {
        EmptyComponent()
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(defaultPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text(
                text = stringResource(R.string.sports),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(list) { sport ->
            SportItem(sport) {
                onClickItem(sport.name)
            }
        }

    }
}

@Composable
fun SportItem(item: SportUI, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 8.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = item.urlImage,
                contentDescription = item.name,
                placeholder = rememberVectorPainter(Lucide.LoaderCircle),
                error = rememberVectorPainter(Lucide.TriangleAlert),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            ),
                            startY = 100f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(defaultPadding),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(defaultPadding))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(defaultPadding)
                ) {
                    Text(
                        text = stringResource(R.string.see_leagues),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Icon(
                        imageVector = Lucide.Forward,
                        contentDescription = stringResource(R.string.acc_enter_item),
                        tint = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
private fun SuccessComponentPreview() {
    SuccessComponent(listOf(
        SportUI(
            id = 0,
            name = "Futebol",
            urlImage = ""
        )
    )) {}
}

