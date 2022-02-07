package com.adamcs.formula1.ui.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adamcs.formula1.data.model.News
import com.adamcs.formula1.ui.viewmodel.NewsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamcs.formula1.R
import com.adamcs.formula1.ui.components.CircularLoadingSpinner
import com.adamcs.formula1.ui.components.ExpandableTextCard
import com.adamcs.formula1.ui.components.Header
import com.adamcs.formula1.ui.components.SearchBar
import com.adamcs.formula1.ui.theme.scarlet

@ExperimentalMaterialApi
@Composable
fun NewsScreen(
    newsModel: NewsViewModel = hiltViewModel()
) {
    Column {
        Header(title = stringResource(R.string.news))

        if (!newsModel.isLoaded.value){
            CircularLoadingSpinner()
        } else {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholderText = stringResource(R.string.search_placeholder)
                            ){
                newsModel.filterNews(it)
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(newsModel.filteredNewsList.value) { news ->
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        NewsCard(news)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun NewsCard(news: News){
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))

    ExpandableTextCard(
        title = news.title,
        description = news.description,
    ){
        Log.d("ASD", "ASD")
        context.startActivity(intent)
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun NewsScreenPreview() {
    NewsScreen()
}