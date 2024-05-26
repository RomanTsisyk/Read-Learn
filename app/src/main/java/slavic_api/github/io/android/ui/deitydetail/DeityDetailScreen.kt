package slavic_api.github.io.android.ui.deitydetail

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import slavic_api.github.io.android.data.model.Deity
import slavic_api.github.io.android.data.model.Result
import slavic_api.github.io.android.ui.deitiesList.DeitiesList
import slavic_api.github.io.android.ui.deitiesList.ErrorView
import slavic_api.github.io.android.ui.deitiesList.LoadingView
import slavic_api.github.io.android.utils.addBaseURL
import slavic_api.github.io.android.utils.transformUrl

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DeityDetailScreen(deityId: String) {
    val viewModel: DeityDetailViewModel = hiltViewModel()
    val deity by viewModel.deity.observeAsState()
    LaunchedEffect(deityId) {
        viewModel.fetchDeity(deityId)
    }
    when (val result = deity) {

        is Result.Loading -> LoadingView()
        is Result.Success<*> -> DeityDetail(deity = result.data as Deity)
        is Result.Error -> ErrorView(error = result.exception)
        else -> {}
    }
}


@Composable
fun DeityDetail(deity: Deity) {

    val imageURl = deity.detailImageUrl.addBaseURL().transformUrl()
    Log.d("Roman", "$deity = deity")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = imageURl,
            contentDescription = deity.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = deity.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = deity.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Domains: ${
                deity.domains.joinToString(", ")
            }",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Symbols: ${deity.symbols.joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Element: ${deity.element}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

}
