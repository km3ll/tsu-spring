package pod.tsu.spring.redis.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.redis.rest.dto.ItemDto;
import pod.tsu.spring.redis.rest.dto.ResponseDto;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	private final ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.INDENT_OUTPUT);

	private final Cache cache;

	public ShopController(RedisCacheManager redisCacheManager) {
		this.cache = redisCacheManager.getCache("ecommerce");
	}

	@PostMapping("item")
	public ResponseEntity<ResponseDto> save(@RequestBody ItemDto itemDto) throws Exception {
		logger.info("Saving item: {}", itemDto);
		String key = itemDto.id();
		cache.put(key, mapper.writeValueAsString(itemDto));
		return ResponseEntity.ok(new ResponseDto("Item created with ID: " + itemDto.id()));
	}

	@GetMapping("item/{id}")
	public ResponseEntity<ItemDto> findById(@PathVariable("id") String id) throws Exception {
		logger.info("Finding item by ID: {}", id);
		String key = id;
		Optional<Cache.ValueWrapper> result = Optional.ofNullable(cache.get(key));
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		ItemDto itemDto = mapper.readValue((String) result.get().get(), ItemDto.class);
		return ResponseEntity.ok(itemDto);
	}

}
