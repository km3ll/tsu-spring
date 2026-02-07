package pod.tsu.spring.files.model.credentials;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Credentials {

	private final String username;

	private final String password;

	private Credentials(String password, String username) {
		this.password = password;
		this.username = username;
	}

	public static Credentials of(String username, String password) {
		if (!StringUtils.hasText(username)) {
			throw new IllegalArgumentException("Username cannot be empty");
		}
		if (!StringUtils.hasText(password)) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		return new Credentials(password, username);
	}

}
