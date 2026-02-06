package pod.tsu.spring.redis.model;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private String country;

	private String id;

	private String name;

}