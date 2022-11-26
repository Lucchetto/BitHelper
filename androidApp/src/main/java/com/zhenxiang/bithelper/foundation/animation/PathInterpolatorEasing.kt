package com.zhenxiang.bithelper.foundation.animation

import android.graphics.Path
import android.view.animation.PathInterpolator
import androidx.compose.animation.core.Easing
import androidx.core.graphics.PathParser

class PathInterpolatorEasing(path: Path): Easing {

    private val interpolator = PathInterpolator(path)

    override fun transform(fraction: Float) = interpolator.getInterpolation(fraction)

    companion object
}

fun PathInterpolatorEasing.Companion.fromPathData(pathData: String) = PathInterpolatorEasing(PathParser.createPathFromPathData(pathData))
