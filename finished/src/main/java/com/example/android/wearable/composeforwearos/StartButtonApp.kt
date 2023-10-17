package com.example.android.wearable.composeforwearos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.scrollAway
import com.example.android.wearable.composeforwearos.theme.WearAppTheme

@Composable
fun StartButtonApp(
    case: Case ,//0:運動の記録　1:振動機能の使用
    strength:Case
) {
    WearAppTheme {
        val listState = rememberLazyListState()

        /* *************************** Part 4: Wear OS Scaffold *************************** */
        Scaffold (
            timeText = {TimeText(modifier = Modifier.scrollAway(listState))}
        ) {
            // Modifiers used by our Wear composables.
            val contentModifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            val iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)

            /* *************************** Part 3: ScalingLazyColumn *************************** */
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 32.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 32.dp
                ),
                verticalArrangement = Arrangement.Center,
                state = listState
            ) {

                /* ******************* Part 1: Simple composables ******************* */

                if (case == Case.REC) {
                    item { TextStart(contentModifier, Case.REC, strength) }
                    item { StartButton(contentModifier, iconModifier, Case.REC, strength) }
                } else if (case == Case.USE) {
                    item { TextStart(contentModifier, Case.USE, strength) }
                    item { StartButton(contentModifier, iconModifier, Case.USE,strength) }
                }

            }
        }
    }
}
