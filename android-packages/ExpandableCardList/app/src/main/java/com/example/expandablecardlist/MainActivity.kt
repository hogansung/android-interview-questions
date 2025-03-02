package com.example.expandablecardlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.expandablecardlist.ui.theme.ExpandableCardListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpandableCardListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpandableCardList()
                }
            }
        }
    }
}

/*
Objective:
Create a list of cards using LazyColumn. Each card shows a title and can be tapped to expand and
reveal additional details with a smooth animation.
*/
@Composable
fun ExpandableCardList() {
    // TODO: Create a list of card data (e.g., a list of title/description pairs).
    // TODO: Display the cards in a LazyColumn.
    // TODO: For each card, allow the card to be tapped to toggle between expanded and collapsed states.
    // TODO: Animate the expansion/collapse of the additional details.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpandableCardListTheme {
        ExpandableCardList()
    }
}