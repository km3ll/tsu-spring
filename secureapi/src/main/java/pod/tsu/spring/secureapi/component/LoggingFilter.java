package pod.tsu.spring.secureapi.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "LoggingFilter", urlPatterns = {"/blog/*"})
public class LoggingFilter extends OncePerRequestFilter {

    public LoggingFilter() {
        log.info("Created");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String correlationID = UUID.randomUUID().toString();
        log.info("{} Request method: {}, URI: {}", correlationID, request.getMethod(), request.getRequestURI());

        long timeInMillis = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        timeInMillis = System.currentTimeMillis() - timeInMillis;

        log.info("{} Response status: {}, content type: {}, duration: {}ms", correlationID, response.getStatus(), response.getContentType(), timeInMillis);

    }
}