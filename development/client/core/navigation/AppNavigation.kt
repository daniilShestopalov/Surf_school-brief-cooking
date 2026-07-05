package com.surfschool.core.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition

@Composable
fun AppNavigation(initialScreen: Screen) {
    // BottomSheetNavigator provided by Voyager allows nested BottomSheet navigation natively in compose
    BottomSheetNavigator {
        Navigator(screen = initialScreen) { navigator ->
            SlideTransition(navigator)
        }
    }
}
