package pod.tsu.spring.containers.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@Tag("Performance")
@ActiveProfiles({"dev"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ContainerServerTest {

    private static final String cassandraName = "cassandra:3.11.2";
    private static final String kafkaName = "confluentinc/cp-kafka:5.4.3";
    private static final String rabbitMQName = "rabbitmq:latest";

    @Container
    private static final CassandraContainer cassandra = new CassandraContainer(DockerImageName.parse(cassandraName));

    @Container
    private static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse(kafkaName));

    @Container
    private static final RabbitMQContainer rabbitMQ = new RabbitMQContainer(DockerImageName.parse(rabbitMQName));

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("kafka.local.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Test
    @DisplayName("Cassandra server starts")
    public void cassandraServer_starts() {
        assertTrue(cassandra.isRunning());
    }

    @Test
    @DisplayName("Kafka server starts")
    public void kafkaServer_starts() {
        assertTrue(kafka.isRunning());
    }

    @Test
    @DisplayName("RabbitMQ server starts")
    public void rabbitServer_starts() {
        assertTrue(rabbitMQ.isRunning());
    }

}