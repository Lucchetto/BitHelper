package com.zhenxiang.bithelper.form

import com.zhenxiang.bithelper.shared.utils.removeSpacesAndNewLines

object StringTransformations {

    val REMOVE_WHITESPACES_AND_NEWLINES: ((String) -> String) = { it.removeSpacesAndNewLines() }
}
