package pod.tsu.spring.containers.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class ContainersConfig {

    private final Logger logger = LoggerFactory.getLogger(ContainersConfig.class);

    private final CassandraContainer cassandra = (CassandraContainer)
        new CassandraContainer(DockerImageName.parse("cassandra:3.11.2"));

    private final RabbitMQContainer rabbitMQ =
        new RabbitMQContainer(DockerImageName.parse("rabbitmq:latest"))
            .withPluginsEnabled("rabbitmq_management");

    private final KafkaContainer kafka =
        new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));


    public ContainersConfig() {
        logger.info("Created");
    }

    @PostConstruct
    private void postConstruct() {

        logger.info("Starting Cassandra");
        cassandra.start();
        logger.info("Cassandra's mapped ports: 9042={}", cassandra.getMappedPort(9042));

        logger.info("Starting Kafka");
        kafka.start();
        logger.info("Kafka's mapped ports: 9093={}", kafka.getMappedPort(9093));

        logger.info("Starting RabbitMQ");
        rabbitMQ.start();
        logger.info("RabbitMQ's mapped ports: 15672={}", rabbitMQ.getMappedPort(15672));

    }

    @PreDestroy
    private void preDestroy() {

        logger.info("Stopping Cassandra");
        cassandra.stop();

        logger.info("Stopping Kafka");
        kafka.stop();

        logger.info("Stopping RabbitMQ");
        rabbitMQ.stop();

    }

}