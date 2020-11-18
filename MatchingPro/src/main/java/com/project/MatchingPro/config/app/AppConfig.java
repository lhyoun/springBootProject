package com.project.MatchingPro.config.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String root = System.getProperty("user.dir") + "/images/";
		registry.addResourceHandler("/image/**").addResourceLocations("file:"+root);
	}
}
