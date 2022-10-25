package vn.kamereo.entity.utils

import java.text.DecimalFormat

val moneyFormatter = DecimalFormat("0").apply {
    this.maximumFractionDigits = 0
    this.minimumFractionDigits = 0
}

val quantityFormatter = DecimalFormat("0").apply {
    this.maximumFractionDigits = 2
    this.minimumFractionDigits = 0
}
