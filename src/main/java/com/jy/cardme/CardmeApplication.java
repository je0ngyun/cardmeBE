package com.jy.cardme;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CardmeApplication {
    private static final String APPLICATION =
                    "spring.config.location=" +
                    "classpath:/application.yml," +
                    "classpath:/private.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(CardmeApplication.class).properties(APPLICATION).run(args);
    }
}
