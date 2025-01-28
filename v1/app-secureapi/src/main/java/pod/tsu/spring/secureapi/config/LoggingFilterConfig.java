package pod.tsu.spring.secureapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Slf4j
@Configuration
public class LoggingFilterConfig {

    public LoggingFilterConfig() {
        log.info("Created");
    }

    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {

        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(false);
        return filter;

    }

}