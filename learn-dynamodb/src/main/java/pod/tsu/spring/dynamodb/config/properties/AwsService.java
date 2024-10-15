package pod.tsu.spring.dynamodb.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = AwsService.PREFIX)
public class AwsService {

    public static final String PREFIX = "aws.service";

    private String endpoint;
    private String region;

}