package pod.tsu.spring.redis.config.v1;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.ClientResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import pod.tsu.spring.redis.config.props.ClientProperties;
import pod.tsu.spring.redis.credentials.Credentials;
import pod.tsu.spring.redis.credentials.FileMonitorCredentialsProvider;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Consumer;

@Configuration
@ConditionalOnProperty(name = "feature.cache-v1.enabled", havingValue = "true")
public class CacheV1Config {

	private static final Logger logger = LoggerFactory.getLogger(CacheV1Config.class);

	@Bean
	public ClientOptions redisClientOptions() {
		return ClientOptions.builder()
			.disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
			.autoReconnect(true)
			.build();
	}

	@Bean
	public LettucePoolingClientConfiguration lettucePoolingClientConfiguration(ClientOptions redisClientOptions,
			ClientProperties clientProperties, ClientResources redisClientResources) {
		logger.info("Building LettucePoolingClientConfiguration");
		return LettucePoolingClientConfiguration.builder()
			.commandTimeout(clientProperties.getReadTimeout())
			.poolConfig(clientProperties.getPooling().getPoolConfig())
			.clientOptions(redisClientOptions)
			.clientResources(redisClientResources)
			.build();
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory(ClientProperties clientProperties,
			LettucePoolingClientConfiguration lettucePoolingClientConfiguration) {

		logger.info("Building LettuceConnectionFactory");

		RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(clientProperties.getNodes());
		clusterConfig.setUsername("default");
		clusterConfig.setPassword("your_strong_password");
		var lettuceConnectionFactory = new LettuceConnectionFactory(clusterConfig, lettucePoolingClientConfiguration);

		if (clientProperties.isHasAuth()) {
			Consumer<Credentials> onCredentialsLoaded = credentials -> {
				logger.warn("#### onCredentialsLoaded: {}", credentials);
				Optional.ofNullable(lettuceConnectionFactory.getClusterConfiguration()).ifPresent(config -> {
					config.setUsername(credentials.username());
					config.setPassword(credentials.password());
				});
				lettuceConnectionFactory.afterPropertiesSet();
			};

			FileMonitorCredentialsProvider.create(clientProperties.getCredentialsFilePath(), onCredentialsLoaded);
		}

		return lettuceConnectionFactory;

	}

	@Bean
	public RedisCacheManager cacheManager(LettuceConnectionFactory connectionFactory) {

		logger.info("Building RedisCacheManager");
		RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofMinutes(20))
			.disableCachingNullValues()
			.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

		return new RedisCacheManager(cacheWriter, config);

	}

}
