package com.example.expandablebottomsheet

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
import com.example.expandablebottomsheet.ui.theme.ExpandableBottomSheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpandableBottomSheetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpandableBottomSheet(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
Objective:
Implement an expandable bottom sheet that the user can drag upward to reveal more content and
collapse downward. The sheet should smoothly animate and snap to either a collapsed or expanded
state once the drag ends.
 */

@Composable
fun ExpandableBottomSheet(modifier: Modifier = Modifier) {
    // TODO: Define state variables to track the current height/offset of the bottom sheet.
    // TODO: Use pointerInput to implement vertical drag gestures.
    // TODO: Snap the bottom sheet to either a collapsed or expanded state when the drag ends.
    // TODO: Display some sample content inside the bottom sheet.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpandableBottomSheetTheme {
        ExpandableBottomSheet()
    }
}