package vn.kamereo.entity.utils

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class StringKtTest {

    @ParameterizedTest
    @MethodSource("testNameNormalizerTestProvider")
    internal fun testNameNormalizer(original: String, normalized: String) {
        Assertions.assertThat(nameNormalizer(original)).isEqualTo(normalized)
    }

    companion object {
        @JvmStatic
        fun testNameNormalizerTestProvider(): Stream<Arguments> = Stream.of(
            Arguments.of("Cửa hàng 16: SUKIYA GIGA MALL", "cửa hàng 16: sukiya giga mall"),
            Arguments.of("SOL Kitchen", "sol kitchen")
        )
    }
}
