package tsu.pod.sandbox.dynamo.config;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.GenericContainer;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Slf4j
@Configuration
@Profile("dynamodb")
public class DynamoClientConfig {

	public DynamoClientConfig() {
		log.info("Initialized");
	}

	@Bean
	DynamoDbClient dynamoDbClient(GenericContainer<?> dynamoDbContainer) {
		String endpoint = String.format("http://%s:%d", dynamoDbContainer.getHost(),
				dynamoDbContainer.getMappedPort(8000));

		DynamoDbClient client = DynamoDbClient.builder()
			.endpointOverride(URI.create(endpoint))
			.region(Region.US_EAST_1)
			.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy", "dummy")))
			.build();

		log.info("DynamoDbClient created");
		return client;
	}

	@Bean
	DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
		DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();

		log.info("DynamoDbEnhancedClient created");
		return enhancedClient;
	}

}
