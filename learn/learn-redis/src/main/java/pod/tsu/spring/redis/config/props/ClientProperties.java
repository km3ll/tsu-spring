package pod.tsu.spring.redis.config.props;

import java.time.Duration;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "redis.client")
public class ClientProperties {

	private List<String> nodes;

	private String credentialsFilePath;

	private boolean useSsl;

	private boolean hasAuth;

	private Duration readTimeout;

	private PoolingProperties pooling;

}
