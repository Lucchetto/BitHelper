package com.zhenxiang.bithelper.form

object StringTransformations {

    val REMOVE_WHITESPACES_AND_NEWLINES: ((String) -> String) = { it.replace("\\s".toRegex(), "") }
}
