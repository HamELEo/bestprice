package ru.bestprice.backend.apiinterface.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ApiController {

    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.selenium.serviceId}")
    private String seleniumIdService;

    @Value("${service.selenium.qrLink}")
    private String seleniumQrCodeLink;

    @PostMapping(value = "qrcode", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQrCode(@RequestBody Map<String, String> body) {
        System.out.println(body);
        Application application = eurekaClient.getApplication(seleniumIdService);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url= "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + seleniumQrCodeLink;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            if (exchange.getStatusCodeValue() == 200) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
        }
    }
}
