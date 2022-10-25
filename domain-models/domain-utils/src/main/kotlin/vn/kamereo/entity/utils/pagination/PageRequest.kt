package vn.kamereo.entity.utils.pagination

@Suppress("DataClassPrivateConstructor")
data class PageRequest private constructor(
    val number: Int,
    val size: Int
) {
    companion object {
        @JvmStatic
        fun create(number: Int, size: Int): PageRequest {
            if (number < 0) {
                throw IllegalArgumentException("number is zero-index. A negative number is not allowed.")
            }
            if (size <= 0) {
                throw IllegalArgumentException("Size must be positive.")
            }
            return PageRequest(number, size)
        }
    }

    fun offset(): Int {
        return number * size
    }
}
