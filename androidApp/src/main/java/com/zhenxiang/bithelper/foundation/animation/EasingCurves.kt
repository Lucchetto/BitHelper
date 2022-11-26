package com.zhenxiang.bithelper.foundation.animation

object EasingCurves {

    // Path data from https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-13.0.0_r15/core/res/res/interpolator/fast_out_extra_slow_in.xml
    val FastOutExtraSlowIn = PathInterpolatorEasing.fromPathData(
        "M 0,0 C 0.05, 0, 0.133333, 0.06, 0.166666, 0.4 C 0.208333, 0.82, 0.25, 1, 1, 1"
    )
}