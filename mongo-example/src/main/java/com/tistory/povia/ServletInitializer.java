package com.tistory.povia;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	protected SpringApplicationBuilder config(SpringApplicationBuilder application) {
		return application.sources(MongoExampleApplication.class);
	}
}
