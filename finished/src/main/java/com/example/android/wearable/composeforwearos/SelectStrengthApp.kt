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
    case: Case, //0:運動の記録　1:振動機能の使用
    toStartButtonAppCaseRecB:() -> Unit = {},
    toStartButtonAppCaseRecM:() -> Unit = {},
    toStartButtonAppCaseRecS:() -> Unit = {},
    toStartButtonAppCaseUseB:() -> Unit = {},
    toStartButtonAppCaseUseM:() -> Unit = {},
    toStartButtonAppCaseUseS:() -> Unit = {},

) {
    WearAppTheme {
        val listState = rememberScalingLazyListState()
        /* *************************** Part 4: Wear OS Scaffold *************************** */
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
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                autoCentering = AutoCenteringParams(itemIndex = 0),
                state = listState
            ) {

                /* ******************* Part 1: Simple composables ******************* */

                if (case == Case.REC){
                    item { TextSelectStrength(contentModifier,Case.REC) }
                    item{ BigChip(contentModifier, iconModifier, {toStartButtonAppCaseRecB()},Case.REC) }
                    item{ MediumChip(contentModifier, iconModifier, {toStartButtonAppCaseRecM()},  Case.REC)}
                    item{ SmallChip(contentModifier, iconModifier, {toStartButtonAppCaseRecS()}, Case.REC)}
                }else if(case == Case.USE){
                    item { TextSelectStrength(contentModifier,Case.USE) }
                    item{ BigChip(contentModifier, iconModifier, {toStartButtonAppCaseUseB()},Case.USE)}
                    item{ MediumChip(contentModifier, iconModifier, {toStartButtonAppCaseUseM()}, Case.USE)}
                    item{ SmallChip(contentModifier, iconModifier, {toStartButtonAppCaseUseS()}, Case.USE)}
                }

            }
        }
    }
}
