package ru.bestprice.backend.selenium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.bestprice.backend.selenium.config.ProxyCustomizer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SeleniumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumApplication.class, args);
	}

	/*@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.additionalCustomizers(new ProxyCustomizer()).build();
	}*/
    /*@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }*/
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate template = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> converters = template.getMessageConverters();
        for(HttpMessageConverter<?> converter : converters){
            if(converter instanceof MappingJackson2HttpMessageConverter){
                try{
                    ((MappingJackson2HttpMessageConverter) converter).setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML, MediaType.TEXT_HTML));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return template;
    }
	/*@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.254.20", 3142));
		requestFactory.setProxy(proxy);

		return new RestTemplate(requestFactory);
	}*/

}
