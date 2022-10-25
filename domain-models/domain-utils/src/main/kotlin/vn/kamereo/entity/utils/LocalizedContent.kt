package vn.kamereo.entity.utils

import java.util.Locale

data class LocalizedContent(
    val en: String,
    val vi: String
) {
    operator fun get(lang: String): String = when (lang) {
        Locale("en").language -> en
        else -> vi
    }
}
