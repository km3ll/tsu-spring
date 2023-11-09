package pod.tsu.spring.containers;

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
class ContainersAppTest {

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
    @DisplayName("Cassandra container starts")
    public void cassandraContainer_starts() {
        assertTrue(cassandra.isRunning());
    }

    @Test
    @DisplayName("Kafka container starts")
    public void kafkaContainer_starts() {
        assertTrue(kafka.isRunning());
    }

    @Test
    @DisplayName("RabbitMQ container starts")
    public void rabbitContainer_starts() {
        assertTrue(rabbitMQ.isRunning());
    }

}