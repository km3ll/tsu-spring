package tsu.pod.sandbox.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
public class HttpLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

		long startTime = System.currentTimeMillis();

		try {
			filterChain.doFilter(wrappedRequest, wrappedResponse);
		}
		finally {
			long duration = System.currentTimeMillis() - startTime;
			log.debug(getTemplate(), request.getMethod(), request.getRequestURI(), request.getQueryString(),
					format(wrappedRequest), response.getStatus(), format(wrappedResponse), duration);
			wrappedResponse.copyBodyToResponse();
		}
	}

	private String format(ContentCachingRequestWrapper request) {
		return new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
	}

	private String format(ContentCachingResponseWrapper response) {
		return new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
	}

	private String getTemplate() {
		return """

				HTTP Request:
				  method={}
				  uri={}
				  query={}
				  body={}
				HTTP Response:
				  status={}
				  body={}
				Duration:
				  ms={}
				=====
				""";
	}

}
