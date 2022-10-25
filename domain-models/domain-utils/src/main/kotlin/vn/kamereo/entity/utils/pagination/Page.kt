package vn.kamereo.entity.utils.pagination

class Page<T> private constructor(val data: List<T>, private val pageResult: PageResult) {
    companion object {
        @JvmStatic
        fun <T> create(data: List<T>, pageRequest: PageRequest, totalCount: Int): Page<T> = Page<T>(
            data,
            PageResult(totalCount = totalCount, size = pageRequest.size)
        )
    }

    fun totalPages() = pageResult.totalPages()

    fun totalResults() = pageResult.totalCount
}
