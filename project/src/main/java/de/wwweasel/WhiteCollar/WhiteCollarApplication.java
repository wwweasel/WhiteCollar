package de.wwweasel.WhiteCollar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WhiteCollarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteCollarApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() 
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**").allowedMethods("GET","POST","DELETE","OPTIONS");//.allowedOrigins("http://localhost:8080");
            	
            }
        };
    }
}
