package pod.tsu.spring.redis;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import pod.tsu.spring.redis.model.Item;

public class Generator {

    private static final Random random = new DefaultRandom();

    private Generator() {
    }

    public static Item genItem() {
        return Item.builder()
            .country(random.oneOf("CAN", "COL", "USA"))
            .id(String.valueOf(random.intRange(10000, 99999)))
            .name(random.alphanumeric(10))
            .build();
    }


}
