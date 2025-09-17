package com.baeldung.ls.actuate.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (isCustomUp()) {

            Map<String, Object> details = new HashMap<>();
            details.put("status", "UP");
            details.put("message", "Custom health indicator up");
            details.put("timestamp", System.currentTimeMillis());
            details.put("statusCode", 200);

            return Health.up()
                .withDetails(details)
                .build();

        } else {
            return Health.down()
                .withDetail("Error Code", 503)
                .build();
        }
    }

    private boolean isCustomUp() {
        return true;
    }

}
