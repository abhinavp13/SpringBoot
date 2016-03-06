package com.pabhinav;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ExpressionException;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * @author pabhinav
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static String ROOT = "upload-dir";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner runner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                try {
                    new File(ROOT).mkdir();
                } catch (Exception e){
                    log.info(" Found Error while creating directory ", e.getMessage());
                }
                log.info("File Successfully Created ", System.getProperty("user.dir"));
            }
        };
    }
}