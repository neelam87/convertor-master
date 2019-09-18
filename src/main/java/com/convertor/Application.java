package com.convertor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author neelam
 *
 */
@SpringBootApplication
@Import({SwaggerConfig.class})
public class Application extends WebMvcConfigurerAdapter{

    protected Application() {
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
