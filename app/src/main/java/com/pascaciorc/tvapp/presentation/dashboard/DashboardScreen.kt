package com.pascaciorc.tvapp.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun DashboardScreen(
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding()
    ) {
        items(items = sampleCategories) { category ->
            CategorySection(category)
        }
    }
}

@Composable
fun CategorySection(category: Category) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = category.title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(category.items) { item ->
                MediaTile(item)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MediaTile(item: MediaItem) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        GlideImage(
            model = item.thumbnailUrl,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 4.dp),
            maxLines = 1
        )
    }
}

private val sampleCategories = listOf(
    Category(
        id = "1",
        title = "Trending Now",
        items = List(10) {
            MediaItem("$it", "Movie $it", "https://picsum.photos/400/600?random=$it")
        }
    ),
    Category(
        id = "2",
        title = "New Releases",
        items = List(10) {
            MediaItem("$it", "New $it", "https://picsum.photos/400/600?blur=$it")
        }
    ),
    Category(
        id = "3",
        title = "Recommended for You",
        items = List(10) {
            MediaItem("$it", "Show $it", "https://picsum.photos/400/600?grayscale&$it")
        }
    )
)