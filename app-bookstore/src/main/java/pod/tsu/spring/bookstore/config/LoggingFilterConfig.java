package pod.tsu.spring.bookstore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingFilterConfig {

    private final Logger logger = LoggerFactory.getLogger(LoggingFilterConfig.class);

    public LoggingFilterConfig() {
        logger.info("Created");
    }

    @Bean
    public CommonsRequestLoggingFilter loggingFilter() {

        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(false);
        return filter;

    }

}
