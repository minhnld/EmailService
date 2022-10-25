package vn.kamereo.graphql_api_service.web.spring.config

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

const val HCMC_ZONE_STR = "Asia/Ho_Chi_Minh"
val HCMC_ZONE_ID: ZoneId = ZoneId.of(HCMC_ZONE_STR)

@Configuration
class SystemConfig {
    @Bean
    @ConditionalOnMissingBean(Clock::class)
    fun clock(): Clock = Clock.system(HCMC_ZONE_ID)

    @Suppress("InjectDispatcher")
    @Bean
    @ConditionalOnMissingBean(CoroutineDispatcher::class)
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
