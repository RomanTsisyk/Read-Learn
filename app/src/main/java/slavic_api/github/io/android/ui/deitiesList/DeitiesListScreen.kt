package slavic_api.github.io.android.ui.deitiesList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import slavic_api.github.io.android.data.model.Deity
import slavic_api.github.io.android.data.model.Result
import slavic_api.github.io.android.utils.addBaseURL
import slavic_api.github.io.android.utils.transformUrl


@Composable
fun DeitiesListScreen(onDeityClick: (String) -> Unit) {
    val viewModel: DeitiesListViewModel = hiltViewModel()
    val deitiesState by viewModel.deities.observeAsState()

//    val filteredDeities = deitiesState.filter  {
//        it.name.contains(searchQuery, ignoreCase = true)
//    }


    when (val result = deitiesState) {
        is Result.Loading -> LoadingView()
        is Result.Success<*> -> DeitiesList(deities = result.data as List<Deity>, onDeityClick)
        is Result.Error -> ErrorView(error = result.exception)
        else -> {}
    }
}

@Composable
fun LoadingView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun DeitiesList(deities: List<Deity>, onDeityClick: (String) -> Unit) {
    val searchQuery by remember { mutableStateOf("") }
    val filteredDeities = deities.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(deities.size) { index ->
            DeityListItem(god = deities[index], onClick = onDeityClick)
        }
    }
}

@Composable
fun DeityListItem(god: Deity, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(god.id) },
        ) {
        val imageURL = god.thumbnailUrl.addBaseURL().transformUrl()
        Column(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = imageURL,
                contentDescription = "${god.name} thumbnail",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = god.name, style = MaterialTheme.typography.titleMedium)
            Text(text = god.description)
        }
    }
}

@Composable
fun ErrorView(error: Exception) {
    Text(text = "Error: ${error.message}", color = Color.Red)
}