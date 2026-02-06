package pod.tsu.spring.redis.config.props;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PoolingProperties {

	private int minIdlePool;

	private int maxIdlePool;

	private int maxTotalPool;

	private Duration maxWait;

	public <T> GenericObjectPoolConfig<T> getPoolConfig() {
		GenericObjectPoolConfig<T> config = new GenericObjectPoolConfig<>();
		config.setMaxTotal(maxTotalPool);
		config.setMaxIdle(maxIdlePool);
		config.setMinIdle(minIdlePool);
		config.setMaxWait(maxWait);
		return config;
	}

}
