/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.wearable.composeforwearos

import android.app.FragmentManager.BackStackEntry
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.scrollAway
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.example.android.wearable.composeforwearos.theme.WearAppTheme
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

/**
 * This code lab is meant to help existing Compose developers get up to speed quickly on
 * Compose for Wear OS.
 *
 * The code lab walks through a majority of the simple composables for Wear OS (both similar to
 * existing mobile composables and new composables).
 *
 * It also covers more advanced composables like [ScalingLazyColumn] (Wear OS's version of
 * [LazyColumn]) and the Wear OS version of [Scaffold].
 *
 * Check out [this link](https://android-developers.googleblog.com/2021/10/compose-for-wear-os-now-in-developer.html)
 * for more information on Compose for Wear OS.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp()
            }
        }
}
enum class Case{
    USE,
    REC,
    BIG,
    MEDIUM,
    SMALL
}
@Composable
fun WearApp(){
    WearAppTheme {
        //TODO: NavControllerを作る
        val navController = rememberSwipeDismissableNavController()

        SwipeDismissableNavHost(
            navController = navController,
            startDestination = "main"
        ) {
            composable("main") {
                MainApp (
                    toRecordDataApp = {navController.navigate("rec")},
                    toUseFunctionApp = {navController.navigate("use")},
                    toImportHbDataApp = {navController.navigate("import")}
                )
            }
            //TODO 画面遷移時の値の渡し方を考える。USE,REC時に２通り、大中小で合計６通りになってしまう為面倒
            //case 0:運動の記録　1:振動機能の使用
            composable("rec"){
                SelectStrengthApp(Case.REC,
                    toStartButtonAppCaseRecB =  {navController.navigate("startrecbig")},
                    toStartButtonAppCaseRecM = {navController.navigate("startrecmedium")},
                    toStartButtonAppCaseRecS = {navController.navigate("startrecsmall")}
                )

            }
            composable("use"){
                SelectStrengthApp(Case.USE,
                    toStartButtonAppCaseUseB =  {navController.navigate("startusebig")},
                    toStartButtonAppCaseUseM = {navController.navigate("startusemedium")},
                    toStartButtonAppCaseUseS = {navController.navigate("startusesmall")}
                )

            }
            composable("import"){
                ImportHbDataApp()
            }
            composable("startrecbig"){
                StartButtonApp(Case.REC,Case.BIG)
            }
            composable("startrecmedium"){
                StartButtonApp(Case.REC,Case.MEDIUM)
            }
            composable("startrecsmall"){
                StartButtonApp(Case.REC,Case.SMALL)
            }
            composable("startusebig"){
                StartButtonApp(Case.USE,Case.BIG)
            }
            composable("startusemedium"){
                StartButtonApp(Case.USE,Case.MEDIUM)
            }
            composable("startusesmall"){
                StartButtonApp(Case.USE,Case.SMALL)
            }

        }


    }
}


@Composable
fun MainApp(
    toRecordDataApp:() -> Unit,
    toUseFunctionApp:() -> Unit,
    toImportHbDataApp:() -> Unit) {
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
                item { TextExample(contentModifier) }
                /* ********************* Part 2: Wear unique composables ********************* */
                item { RecordDataChip(contentModifier, iconModifier,onNavigateToRecordDataApp = {toRecordDataApp()})}
                item { UseFunctionChip(contentModifier, iconModifier,onNavigateToUseFunctionApp = {toUseFunctionApp()})}
                item { ImportHbDataChip(contentModifier, iconModifier,onNavigateToImportHbDataApp = {toImportHbDataApp()}) }


            }

            // TODO (End): Create a Scaffold (Wear Version)
        }
    }
}



@WearPreviewDevices
@Composable
fun WearAppPreview() {
    WearApp()
}
