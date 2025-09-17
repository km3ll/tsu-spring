package pod.tsu.spring.dynamodb.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = AwsCredentials.PREFIX)
public class AwsCredentials {

    public static final String PREFIX = "aws.credentials";

    private String accessKey;
    private String secretKey;

}
