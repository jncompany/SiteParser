package com.jncompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SiteParserApplication extends SpringBootServletInitializer{

	private static final Logger log = LoggerFactory.getLogger(SiteParserApplication.class);
	
	 @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SiteParserApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SiteParserApplication.class, args);
	}
}
