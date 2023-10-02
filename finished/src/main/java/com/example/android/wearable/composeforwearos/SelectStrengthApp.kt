package com.example.android.wearable.composeforwearos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
fun SelectStrengthApp(
    case: Int //0:運動の記録　1:振動機能の使用
) {
    WearAppTheme {
        // TODO: Swap to ScalingLazyListState
        val listState = rememberScalingLazyListState()

        /* *************************** Part 4: Wear OS Scaffold *************************** */
        // TODO (Start): Create a Scaffold (Wear Version)
        Scaffold(
            timeText = {
                TimeText(modifier = Modifier.scrollAway(listState))
            },
            vignette = {
                // Only show a Vignette for scrollable screens. This code lab only has one screen,
                // which is scrollable, so we show it all the time.
                Vignette(vignettePosition = VignettePosition.TopAndBottom)
            },
            positionIndicator = {
                PositionIndicator(
                    scalingLazyListState = listState
                )
            },


            ) {

            // Modifiers used by our Wear composables.
            val contentModifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            val iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)

            /* *************************** Part 3: ScalingLazyColumn *************************** */
            // TODO: Swap a ScalingLazyColumn (Wear's version of LazyColumn)
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                autoCentering = AutoCenteringParams(itemIndex = 0),
                state = listState
            ) {

                /* ******************* Part 1: Simple composables ******************* */

                if (case == 0){
                    item { TextSelectStrength(contentModifier,0) }
                    item{ BigChip(contentModifier, iconModifier, {},0) }
                    item{ MediumChip(contentModifier, iconModifier, {},  0)}
                    item{ SmallChip(contentModifier, iconModifier, {}, 0)}
                }else if(case ==1){
                    item { TextSelectStrength(contentModifier,1) }
                    item{ BigChip(contentModifier, iconModifier, {},  1)}
                    item{ MediumChip(contentModifier, iconModifier, {}, 1)}
                    item{ SmallChip(contentModifier, iconModifier, {}, 1)}
                }

            }

            // TODO (End): Create a Scaffold (Wear Version)
        }
    }
}