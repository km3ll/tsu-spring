package pod.tsu.spring.redis.config.v2;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.stereotype.Component;
import pod.tsu.spring.redis.config.props.ClientProperties;
import pod.tsu.spring.redis.credentials.Credentials;
import pod.tsu.spring.redis.credentials.FileMonitorCredentialsProvider;

import java.time.Duration;

@Component
@ConditionalOnProperty(name = "feature.cache-v2.enabled", havingValue = "true")
public class RedisConnectionManager {

	private static final Logger logger = LoggerFactory.getLogger(RedisConnectionManager.class);

	private final ClientProperties clientProperties;

	private final ClientResources clientResources;

	private final DynamicRedisConnectionFactory dynamicRedisConnectionFactory;

	public RedisConnectionManager(ClientProperties clientProperties, ClientResources clientResources,
			DynamicRedisConnectionFactory dynamicRedisConnectionFactory) {
		logger.info("Initializing RedisConnectionManager");
		this.clientProperties = clientProperties;
		this.clientResources = clientResources;
		this.dynamicRedisConnectionFactory = dynamicRedisConnectionFactory;
		FileMonitorCredentialsProvider.create(clientProperties.getCredentialsFilePath(), this::reload);
	}

	public synchronized void reload(Credentials credentials) {
		logger.info("Reloading LettuceConnectionFactory");

		var clusterConfig = new RedisClusterConfiguration(clientProperties.getNodes());
		clusterConfig.setMaxRedirects(3);

		clusterConfig.setUsername(credentials.username());
		clusterConfig.setPassword(credentials.password());

		var newLettuceConnectionFactory = new LettuceConnectionFactory(clusterConfig, getPoolingClientConfiguration());
		newLettuceConnectionFactory.afterPropertiesSet();
		dynamicRedisConnectionFactory.set(newLettuceConnectionFactory);
	}

	private LettucePoolingClientConfiguration getPoolingClientConfiguration() {
		logger.info("Building LettucePoolingClientConfiguration");
		return LettucePoolingClientConfiguration.builder()
			.commandTimeout(clientProperties.getReadTimeout())
			.poolConfig(clientProperties.getPooling().getPoolConfig())
			.clientOptions(buildClientOptions())
			.clientResources(clientResources)
			.build();
	}

	private ClusterClientOptions buildClientOptions() {
		return ClusterClientOptions.builder()
			.topologyRefreshOptions(buildTopology())
			.disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
			.autoReconnect(true)
			.build();
	}

	private ClusterTopologyRefreshOptions buildTopology() {
		return ClusterTopologyRefreshOptions.builder()
			.enableAdaptiveRefreshTrigger()
			.enablePeriodicRefresh(Duration.ofSeconds(30))
			.refreshTriggersReconnectAttempts(3)
			.build();
	}

}
