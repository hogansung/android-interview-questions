package com.example.expandablecardlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandablecardlist.ui.theme.ExpandableCardListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpandableCardListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpandableCardList(modifier = Modifier.padding(innerPadding))
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
fun ExpandableCard(card: CardData) {
    // 定義局部狀態，控制卡片是否展開
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            // Animate the expansion/collapse of the additional details.
            .animateContentSize() // 加上動畫效果，展開/收起時平滑過渡尺寸
            .clickable { expanded = !expanded }, // 點擊後切換狀態
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = card.title,
                style = MaterialTheme.typography.titleLarge
            )
            // 只有在展開狀態時顯示描述內容
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = card.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Composable
fun ExpandableCardList(modifier: Modifier = Modifier) {
    // Create a list of card data (e.g., a list of title/description pairs).
    val cardList = remember {
        mutableListOf<CardData>().apply {
            for (letter in 'A'..'Z') {
                add(CardData("Card $letter", "Descripbion for Card $letter"))
            }
        }
    }
    // Display the cards in a LazyColumn.
//    val listState = rememberLazyListState()

    LazyColumn(
//        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // For each card, allow the card to be tapped to toggle between expanded and collapsed states.
        items(cardList) { card ->
            ExpandableCard(card = card)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpandableCardListTheme {
        ExpandableCardList()
    }
}

data class CardData(
    val title: String,
    val description: String
)