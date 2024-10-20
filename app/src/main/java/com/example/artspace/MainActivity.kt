package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {

    val artworks = listOf(
        Artwork(R.drawable.renaissance_nike, "Renaissance Nike", "Louis Durand", "2024"),
        Artwork(R.drawable.the_constructive_soul, "The Constructive Soul", "Sid", "2024"),
        Artwork(R.drawable.dream_of_infinity, "Dream_of_Infinity" , "Sid", "2024"),
    )


    var currentIndex by remember { mutableIntStateOf(0) }


    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtworkImage(currentArtwork.imageRes)


        ArtworkDescription(
            title = currentArtwork.title,
            artist = currentArtwork.artist,
            year = currentArtwork.year
        )


        ArtworkControls(
            onPreviousClick = { if (currentIndex > 0) currentIndex-- },
            onNextClick = { if (currentIndex < artworks.size - 1) currentIndex++ },
            isPreviousEnabled = currentIndex > 0,
            isNextEnabled = currentIndex < artworks.size - 1
        )
    }
}

@Composable
fun ArtworkImage(imageRes: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.8f)
            .aspectRatio(3f / 4f)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Artwork Image",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ArtworkDescription(title: String, artist: String, year: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.8f)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "by $artist",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Text(
            text = "($year)",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

@Composable
fun ArtworkControls(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    isPreviousEnabled: Boolean,
    isNextEnabled: Boolean
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f),
            enabled = isPreviousEnabled,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
        ) {
            Text(text = "Previous", color = Color.White)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f),
            enabled = isNextEnabled,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
        ) {
            Text(text = "Next", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}