package com.pascaciorc.tvapp.presentation.mediaitemdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pascaciorc.tvapp.presentation.dashboard.MediaItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MediaItemDetailsScreen(
    modifier: Modifier,
    mediaItem: MediaItem
    //viewModel: DashboardViewModel = hiltViewModel()
) {
    //val state by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .padding()
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GlideImage(
            model = mediaItem.thumbnailUrl,
            contentDescription = mediaItem.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = mediaItem.title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { },
            modifier = modifier,
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Play",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}