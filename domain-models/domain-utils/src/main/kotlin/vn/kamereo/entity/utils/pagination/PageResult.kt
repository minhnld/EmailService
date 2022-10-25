package vn.kamereo.entity.utils.pagination

import kotlin.math.ceil

data class PageResult(
    val totalCount: Int,
    val size: Int
) {
    fun totalPages(): Int = ceil(totalCount.toDouble().div(size)).toInt()
}
