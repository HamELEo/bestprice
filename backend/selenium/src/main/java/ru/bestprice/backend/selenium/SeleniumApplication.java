package ru.bestprice.backend.selenium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class SeleniumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumApplication.class, args);
	}

    @Bean
    RestTemplate restTemplate() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, converter);
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }
   /*@Bean
   RestTemplate restTemplate() {
       return new RestTemplate();
   }*/
}
