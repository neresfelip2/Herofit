package br.com.neresfelip.herofitbrasil.presentation.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import br.com.neresfelip.herofitbrasil.R
import br.com.neresfelip.herofitbrasil.presentation.model.LeagueDetailUI
import br.com.neresfelip.herofitbrasil.presentation.ui.UIState
import br.com.neresfelip.herofitbrasil.presentation.ui.components.ErrorComponent
import br.com.neresfelip.herofitbrasil.presentation.ui.components.LoadingComponent
import br.com.neresfelip.herofitbrasil.presentation.ui.theme.defaultPadding
import br.com.neresfelip.herofitbrasil.presentation.viewmodel.LeagueDetailViewModel
import coil.compose.AsyncImage
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Forward
import com.composables.icons.lucide.Lucide

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueDetailScreen(
    viewModel: LeagueDetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {

    val leagueDetailState by viewModel.leagueDetailState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar({},
                navigationIcon = {
                    IconButton(onClickBack) {
                        Icon(
                            imageVector = Lucide.ArrowLeft,
                            contentDescription = stringResource(R.string.acc_back_button)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {
        Box(Modifier.padding(bottom = it.calculateBottomPadding())) {
            when (leagueDetailState) {
                UIState.Loading -> LoadingComponent()
                is UIState.Error -> ErrorComponent((leagueDetailState as UIState.Error).description)
                is UIState.Success -> SuccessComponent((leagueDetailState as UIState.Success<LeagueDetailUI>).data)
            }
        }
    }

}

@Composable
private fun SuccessComponent(detail: LeagueDetailUI) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImage(
                    model = detail.urlBanner ?: detail.urlLogo,
                    contentDescription = stringResource(R.string.acc_banner, detail.name),
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
                                    Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(defaultPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                detail.urlLogo?.let { logoUrl ->
                    AsyncImage(
                        model = logoUrl,
                        contentDescription = stringResource(R.string.acc_logo, detail.name),
                        modifier = Modifier
                            .size(100.dp)
                            .offset(y = (-50).dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = detail.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(defaultPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = detail.sport,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )

                    detail.country?.let { country ->
                        Text(text = "•", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            text = country,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        detail.description?.let { description ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = defaultPadding, vertical = defaultPadding/2)
                ) {
                    Column(
                        modifier = Modifier.padding(defaultPadding)
                    ) {
                        Text(
                            text = stringResource(R.string.about),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        if (detail.urlInstagram != null || detail.urlFacebook != null || detail.urlYoutube != null) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = defaultPadding, vertical = defaultPadding/2)
                ) {
                    Column(
                        modifier = Modifier.padding(defaultPadding)
                    ) {
                        Text(
                            text = stringResource(R.string.social_media),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        detail.urlInstagram?.let { url ->
                            SocialMediaItem(
                                resLabel = R.string.instagram,
                                resIcon = R.drawable.ic_instagram,
                                url = url,
                            )
                        }

                        detail.urlFacebook?.let { url ->
                            SocialMediaItem(
                                resLabel = R.string.facebook,
                                resIcon = R.drawable.ic_facebook,
                                url = url,
                            )
                        }

                        detail.urlYoutube?.let { url ->
                            SocialMediaItem(
                                resLabel = R.string.youtube,
                                resIcon = R.drawable.ic_youtube,
                                url = url,
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(defaultPadding/2))
        }

    }
}

@Composable
private fun SocialMediaItem(
    resLabel: Int,
    resIcon: Int,
    url: String,
) {
    val context = LocalContext.current
    val label = stringResource(resLabel)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                context.startActivity(intent)
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(resIcon),
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Lucide.Forward,
            contentDescription = stringResource(R.string.acc_enter_item),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}