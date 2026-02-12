package br.com.neresfelip.herofitbrasil.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import br.com.neresfelip.herofitbrasil.R
import br.com.neresfelip.herofitbrasil.presentation.model.LeagueUI
import br.com.neresfelip.herofitbrasil.presentation.ui.UIState
import br.com.neresfelip.herofitbrasil.presentation.ui.components.ErrorComponent
import br.com.neresfelip.herofitbrasil.presentation.ui.components.LoadingComponent
import br.com.neresfelip.herofitbrasil.presentation.ui.theme.defaultPadding
import br.com.neresfelip.herofitbrasil.presentation.viewmodel.LeagueListViewModel
import coil.compose.AsyncImage
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.LoaderCircle
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.TriangleAlert

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueListScreen(
    viewModel: LeagueListViewModel = hiltViewModel(),
    onClickItem: (idLeague: Int) -> Unit,
    onClickBack: () -> Unit
) {

    val leagueListState by viewModel.leagueListState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClickBack) {
                        Icon(
                            painter = rememberVectorPainter(Lucide.ArrowLeft),
                            contentDescription = stringResource(R.string.acc_back_button)
                        )
                    }
                }
            )
        }
    ) {
        Box(Modifier.padding(it)) {

            when(leagueListState) {
                is UIState.Loading -> LoadingComponent()
                is UIState.Error -> ErrorComponent((leagueListState as UIState.Error).description)
                is UIState.Success -> SuccessComponent((leagueListState as UIState.Success<List<LeagueUI>>).data, onClickItem)
            }

        }
    }

}

@Composable
private fun SuccessComponent(leagues: List<LeagueUI>, onClickItem: (idLeague: Int) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(defaultPadding),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding),
        verticalItemSpacing = defaultPadding
    ) {
        items(leagues) { item ->
            Card(onClick = { onClickItem(item.id) }) {
                Column(
                    Modifier.padding(defaultPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(defaultPadding)
                ) {
                    AsyncImage(
                        model = item.urlImage,
                        contentDescription = item.name,
                        placeholder = rememberVectorPainter(Lucide.LoaderCircle),
                        error = rememberVectorPainter(Lucide.TriangleAlert),
                    )
                    Text(
                        item.name,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}