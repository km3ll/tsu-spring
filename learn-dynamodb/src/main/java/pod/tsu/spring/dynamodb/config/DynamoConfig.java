package pod.tsu.spring.dynamodb.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pod.tsu.spring.dynamodb.config.properties.AwsCredentials;
import pod.tsu.spring.dynamodb.config.properties.AwsService;

@Configuration
public class DynamoConfig {

    @Bean
    public DynamoDBMapper buildAmazonDynamoDB(AwsCredentials awsCredentials, AwsService awsService) {
        var amazonDynamoDB =  AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(buildEndpointConfig(awsService))
            .withCredentials(buildCredentialsProvider(awsCredentials))
            .build();
        return new DynamoDBMapper(amazonDynamoDB);
    }

    private AWSCredentialsProvider buildCredentialsProvider(AwsCredentials awsCredentials) {
        return new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(awsCredentials.getAccessKey(), awsCredentials.getSecretKey())
        );
    }

    private AwsClientBuilder.EndpointConfiguration buildEndpointConfig(AwsService awsService) {
        return new AwsClientBuilder.EndpointConfiguration(awsService.getEndpoint(), awsService.getRegion());
    }

}