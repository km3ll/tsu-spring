package tsu.pod.appmodules.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StringHelperService {

    private final Logger logger = LoggerFactory.getLogger(StringHelperService.class);

    public StringHelperService() {
        logger.info("StringHelperService created");
    }

    public String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}
