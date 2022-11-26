package com.zhenxiang.bithelper.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zhenxiang.bithelper.foundation.animation.EasingCurves

/**
 * Build an animate route with animating similar to Android 13 activity animation
 */
@ExperimentalAnimationApi
fun NavGraphBuilder.animatedRouteComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            slideInHorizontally(
                tween(
                    durationMillis = 450,
                    easing = EasingCurves.FastOutExtraSlowIn
                )
            ) {
                (it / 100) * 10
            }.plus(
                fadeIn(
                    tween(
                        durationMillis = 83,
                        delayMillis = 50,
                        easing = LinearEasing
                    )
                )
            )
        },
        exitTransition = {
            slideOutHorizontally(
                tween(
                    durationMillis = 450,
                    easing = EasingCurves.FastOutExtraSlowIn
                )
            ) {
                (it / 100) * -10
            }
        },
        popEnterTransition = {
            slideInHorizontally(
                tween(
                    durationMillis = 450,
                    easing = EasingCurves.FastOutExtraSlowIn
                )
            ) {
                (it / 100) * -10
            }
        },
        popExitTransition = {
            slideOutHorizontally(
                tween(
                    durationMillis = 450,
                    easing = EasingCurves.FastOutExtraSlowIn
                )
            ) {
                (it / 100) * 10
            }.plus(
                fadeOut(
                    tween(
                        durationMillis = 83,
                        delayMillis = 35,
                        easing = LinearEasing
                    )
                )
            )
        },
        content = content
    )
}
