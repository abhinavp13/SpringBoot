package com.pabhinav;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pabhinav
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public CommandLineRunner runner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("Creating Tables");

                jdbcTemplate.execute("DROP TABLE CUSTOMERS IF EXISTS");
                jdbcTemplate.execute("CREATE TABLE CUSTOMERS(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

                List<Object[]> objectList = new ArrayList<Object[]>();
                objectList.add(new Object[]{"John", "Carter"});
                objectList.add(new Object[]{"Roman", "Nurik"});
                objectList.add(new Object[]{"Vince", "McGrady"});
                objectList.add(new Object[]{"Lionel", "Messi"});

                log.info("Batch update for customer records ...");
                jdbcTemplate.batchUpdate("INSERT INTO CUSTOMERS(first_name, last_name) VALUES (?,?)", objectList);

                log.info("Querying for customer records :");
                List<Map<String,Object>> mapList = jdbcTemplate.queryForList("SELECT * FROM customers");
                log.info(mapList.toString());
            }
        };
    }
}