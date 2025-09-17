package pod.tsu.spring.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import pod.tsu.spring.redis.model.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

    private final Cache cache;

    private final ObjectMapper mapper = new ObjectMapper()
        .disable(SerializationFeature.INDENT_OUTPUT);

    @Autowired
    public ApplicationTest(RedisCacheManager redisCacheManager) {
        this.cache = redisCacheManager.getCache("product-catalog");
    }

    @Test
    @DisplayName("Cache manager should create a cache namespace")
    public void manager_connectsToServer() {
        assertNotNull(cache);
    }

    @Test
    @DisplayName("Cache manager should put and get a new object")
    public void manager_putsNewObject() throws Exception {

        // Given
        Item item = Generator.genItem();
        String key = item.getCountry() + ":" + item.getId();

        // When
        cache.put(key, mapper.writeValueAsString(item));

    }

    @Test
    @DisplayName("Cache manager should get an object")
    public void manager_getsAnObject() throws Exception {

        // Given
        Item item = Generator.genItem();
        String key = item.getCountry() + ":" + item.getId();

        // When
        cache.put(key, mapper.writeValueAsString(item));
        Cache.ValueWrapper result = cache.get(key);

        // Then
        Item cachedItem = mapper.readValue((String) result.get(), Item.class);
        assertEquals(item.getCountry(), cachedItem.getCountry());
        assertEquals(item.getId(), cachedItem.getId());

    }

    @Test
    @DisplayName("Cache manager should evict an object by key")
    public void manager_evictsAnObjectByKey() throws Exception {

        // Given
        Item item = Generator.genItem();
        String key = item.getCountry() + ":" + item.getId();

        // When
        cache.put(key, mapper.writeValueAsString(item));
        cache.evict(key);

        // Then
        Cache.ValueWrapper result = cache.get(key);
        assertNull(result);

    }

    @Test
    @DisplayName("Cache manager should clear all cache entries")
    public void manager_clearAllCacheEntries() throws Exception {

        // Given
        Item item = Generator.genItem();
        String key = item.getCountry() + ":" + item.getId();

        // When
        cache.put(key, mapper.writeValueAsString(item));
        cache.clear();

        // Then
        Cache.ValueWrapper result = cache.get(key);
        assertNull(result);

    }

}