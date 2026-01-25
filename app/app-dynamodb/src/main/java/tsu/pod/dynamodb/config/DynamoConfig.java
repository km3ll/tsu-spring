package tsu.pod.dynamodb.config;

import java.net.URI;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@EnableDynamoDBRepositories(basePackages = "tsu.pod.dynamodb.repository")
public class DynamoConfig {

	private static final String LOCAL_DYNAMO_ENDPOINT = "http://localhost:8000";

	private static final String DUMMY_KEY = "dummyKey";

	private static final String DUMMY_SECRET = "dummySecret";

	private final Logger logger = LoggerFactory.getLogger(DynamoConfig.class);

	public DynamoConfig() {
		logger.info("Initializing DynamoDB clients");
	}

	@Bean
	public DynamoDbClient dynamoDbClient() {
		return DynamoDbClient.builder()
			.region(Region.US_EAST_1) // arbitrary region for local
			.endpointOverride(URI.create(LOCAL_DYNAMO_ENDPOINT))
			.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(DUMMY_KEY, DUMMY_SECRET)))
			.build();
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard()
			// Point to local DynamoDB endpoint
			.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-east-1"))
			// DynamoDB local ignores credentials, but SDK requires them
			.withCredentials(new com.amazonaws.auth.AWSStaticCredentialsProvider(
					new com.amazonaws.auth.BasicAWSCredentials(DUMMY_KEY, DUMMY_SECRET)))
			.build();
	}

}
