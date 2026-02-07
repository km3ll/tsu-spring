package pod.tsu.spring.files.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.credentials")
public class CredentialsProps {

	private String filePath;

}
