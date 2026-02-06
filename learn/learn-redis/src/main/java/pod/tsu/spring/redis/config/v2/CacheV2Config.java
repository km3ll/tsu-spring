package pod.tsu.spring.redis.config.v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@ConditionalOnProperty(name = "feature.cache-v2.enabled", havingValue = "true")
public class CacheV2Config {

	private static final Logger logger = LoggerFactory.getLogger(CacheV2Config.class);

	@Bean
	public RedisCacheManager cacheManager(DynamicRedisConnectionFactory dynamicFactory) {
		logger.info("Building RedisCacheManager");
		var writer = RedisCacheWriter.nonLockingRedisCacheWriter(dynamicFactory);
		var config = RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofMinutes(20))
			.disableCachingNullValues()
			.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
		return new RedisCacheManager(writer, config);
	}

}
