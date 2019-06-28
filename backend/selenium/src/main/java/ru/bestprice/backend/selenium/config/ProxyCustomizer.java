package ru.bestprice.backend.selenium.config;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ProxyCustomizer implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        final String proxyUrl = "192.168.254.20";
        final int port = 3142;

        HttpHost proxy = new HttpHost(proxyUrl, port);
        CloseableHttpClient httpClient = HttpClientBuilder.create().setRoutePlanner(new DefaultProxyRoutePlanner(proxy) {
            @Override
            protected HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
                /*if (target.getHostName().equals("proverkacheka.com")) {
                    return super.determineProxy(target, request, context);
                }
                return null;*/
                return super.determineProxy(target, request, context);
            }
        }).build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

}
