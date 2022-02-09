package com.adamcs.formula1.ui.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adamcs.formula1.data.model.News
import com.adamcs.formula1.ui.viewmodel.NewsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamcs.formula1.R
import com.adamcs.formula1.ui.components.*
import com.adamcs.formula1.ui.theme.scarlet

@ExperimentalMaterialApi
@Composable
fun NewsScreen(
    newsModel: NewsViewModel = hiltViewModel()
) {
    Column {
        CurvedHeader(){
            Box(modifier = Modifier.fillMaxHeight(1f)
                .fillMaxWidth(1f)){
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .width(290.dp)
                        .padding(vertical = 20.dp),
                    painter = painterResource(id = R.drawable.f1_car),
                    contentDescription = "F1 car",
                )
                Text(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.news),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(vertical = 20.dp)
                )
            }
        }

        if (!newsModel.isLoaded.value) {
            CircularLoadingSpinner()
        } else {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholderText = stringResource(R.string.search_placeholder)
            ) {
                newsModel.filterNews(it)
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(newsModel.filteredNewsList.value) { news ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
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
fun NewsCard(news: News) {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))

    ExpandableTextCard(
        title = news.title,
        content = {
            NewsContent(description = news.description)
            {
                context.startActivity(intent)
            }
        },
    )
}

@Composable
private fun NewsContent(
    description: String,
    descriptionFontSize: TextUnit = 12.sp,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 6,
    onClick: () -> Unit = {},
) {
    Text(
        text = description,
        fontSize = descriptionFontSize,
        fontWeight = descriptionFontWeight,
        maxLines = descriptionMaxLines,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 15.sp,
    )

    Spacer(modifier = Modifier.height(20.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.check_full_article),
            color = Color.Gray,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.clickable {
                onClick()
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun NewsScreenPreview() {
    NewsScreen()
}