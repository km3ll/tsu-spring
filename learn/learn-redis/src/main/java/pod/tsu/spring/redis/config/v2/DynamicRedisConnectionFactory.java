package pod.tsu.spring.redis.config.v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
@ConditionalOnProperty(name = "feature.cache-v2.enabled", havingValue = "true")
public class DynamicRedisConnectionFactory implements RedisConnectionFactory {

	private static final Logger logger = LoggerFactory.getLogger(DynamicRedisConnectionFactory.class);

	private final AtomicReference<RedisConnectionFactory> delegate = new AtomicReference<>();

	public void set(RedisConnectionFactory newFactory) {
		logger.info("Set new RedisConnectionFactory");
		RedisConnectionFactory old = delegate.getAndSet(newFactory);
		if (old instanceof DisposableBean disposable) {
			try {
				disposable.destroy();
			}
			catch (Exception ignored) {
			}
		}
	}

	@Override
	public RedisConnection getConnection() {
		logger.info("Get RedisConnection from delegate");
		return delegate.get().getConnection();
	}

	@Override
	public RedisClusterConnection getClusterConnection() {
		logger.info("Get RedisClusterConnection from delegate");
		return delegate.get().getClusterConnection();
	}

	@Override
	public boolean getConvertPipelineAndTxResults() {
		logger.info("Get convertPipelineAndTxResults from delegate");
		return delegate.get().getConvertPipelineAndTxResults();
	}

	@Override
	public RedisSentinelConnection getSentinelConnection() {
		logger.info("Get RedisSentinelConnection from delegate");
		return delegate.get().getSentinelConnection();
	}

	@Override
	public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
		return new UncategorizedDataAccessException("Data access error", ex) {
		};
	}

}
