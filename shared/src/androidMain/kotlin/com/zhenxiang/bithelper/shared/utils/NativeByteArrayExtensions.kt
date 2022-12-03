package com.zhenxiang.bithelper.shared.utils

import android.util.Base64

actual fun ByteArray.toBase64String(): String = Base64.encodeToString(this, Base64.NO_WRAP)
