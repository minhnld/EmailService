package vn.kamereo.entity.utils

import java.text.Normalizer
import java.util.Locale

fun nameNormalizer(value: String): String = Normalizer.normalize(
    value.trim().lowercase(Locale.getDefault()),
    Normalizer.Form.NFKC
)
