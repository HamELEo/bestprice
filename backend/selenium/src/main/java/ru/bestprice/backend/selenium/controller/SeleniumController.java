package ru.bestprice.backend.selenium.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.bestprice.backend.exlibrary.entity.Receipt;
import ru.bestprice.backend.exlibrary.entity.ResponseCheck;
import ru.bestprice.backend.selenium.entity.QrCode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class SeleniumController {

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.EsService.serviceId}")
    private String esServiceId;

    @Value("${service.EsService.qrSaveUrl}")
    private String esServiceQrSaveUrl;

    @Value("${selenium.browser.proxy}")
    private String seleniumProxy;

    @Value("${selenium.browser.path}")
    private String pathToBrowser;

    private Proxy proxy;

    private ChromeOptions options;

    /* @PostMapping(value = "set_qr_code")
     public ResponseEntity<?> setQrCode(@RequestBody Map<String, String> body) {
         Properties systemProperties = System.getProperties();
         systemProperties.setProperty( "http.proxyHost", "192.168.254.20" );
         systemProperties.setProperty( "http.proxyPort", "3142" );
         systemProperties.setProperty( "https.proxyHost", "192.168.254.20" );
         systemProperties.setProperty( "https.proxyPort", "3142" );
         QrCode code = parseQrCode(body.get("code"));
         HttpEntity<String> entity = new HttpEntity<>(createHeaders("+79104266111", "723711"));
         ResponseEntity<String> exchange = restTemplate.exchange("https://proverkacheka.nalog.ru:9999/v1/mobile/users/login", HttpMethod.GET, entity, String.class);
         System.out.println(exchange.getStatusCodeValue());
         return new ResponseEntity<>(HttpStatus.OK);
     }*/

    @PostMapping(value = "set_qr_code")
    public ResponseEntity<?> qrCode(@RequestBody Map<String, String> body) {
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty("http.proxyHost", "192.168.254.20");
        systemProperties.setProperty("http.proxyPort", "3142");
        systemProperties.setProperty("https.proxyHost", "192.168.254.20");
        systemProperties.setProperty("https.proxyPort", "3142");
        String uri = "https://proverkacheka.com/check/get".trim();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody(body.get("code")), getHeaders());
        ResponseEntity<ResponseCheck> result = restTemplate.exchange(uri, HttpMethod.POST, entity, ResponseCheck.class);
        return new ResponseEntity<>(sendToEs(result.getBody()), HttpStatus.OK);
    }

    private ResponseEntity<?> sendToEs(ResponseCheck responseCheck) {
        Receipt receipt = responseCheck.getData().getReceipt();
        Application application = eurekaClient.getApplication(esServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url= "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + esServiceQrSaveUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Receipt> requestEntity = new HttpEntity<>(receipt, headers);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "ENGID=1.1; _ym_uid=1560243443710875796; _ym_d=1560243443; _ym_isad=2");
        headers.set("Origin", "https://proverkacheka.com ");
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.set("Referer", "https://proverkacheka.com/");
        headers.set("X-Requested-With", "XMLHttpRequest");
        headers.set("Connection", "keep-alive");
        headers.set("DNT", "1");
        return headers;
    }

    private MultiValueMap<String, String> requestBody(String qr) {
        QrCode code = parseQrCode(qr);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.put("fn", Arrays.asList(code.getFn()));
        requestBody.put("fd", Arrays.asList(code.getFd()));
        requestBody.put("fp", Arrays.asList(code.getFp()));
        requestBody.put("n", Arrays.asList(code.getN()));
        requestBody.put("s", Arrays.asList(code.getS()));
        requestBody.put("t", Arrays.asList(code.getT()));
        requestBody.put("qr", Arrays.asList("0"));
        return requestBody;
    }
    //получение данных через Selenium
   /* @PostMapping(value = "set_qr_code1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setQrCode(@RequestBody Map<String, String> body) {
        QrCode code = parseQrCode(body.get("code"));
        System.setProperty("webdriver.chrome.driver", pathToBrowser);
        WebDriver webDriver = new ChromeDriver(getChromeOptions());
        webDriver.get("https://proverkacheka.com/");
        webDriver.findElement(By.xpath(".//input[@class='b-check_fn form-control']")).sendKeys(code.getFn());
        webDriver.findElement(By.xpath(".//input[@class='b-check_fd form-control']")).sendKeys(code.getFd());
        webDriver.findElement(By.xpath(".//input[@class='b-check_fp form-control']")).sendKeys(code.getFp());
        webDriver.findElement(By.xpath(".//input[@class='b-check_s form-control']")).sendKeys(code.getS());
        webDriver.findElement(By.xpath(".//input[@class='b-check_t form-control g-active']")).clear();
        webDriver.findElement(By.xpath(".//input[@class='b-check_t form-control g-active']")).sendKeys(code.getT());
        Select dropdown = new Select(webDriver.findElement(By.xpath(".//select[@class='b-check_n form-control']")));
        switch (code.getN()) {
            case "1":
                dropdown.selectByValue("1");
                break;
            case "2":
                dropdown.selectByValue("2");
                break;
            case "3":
                dropdown.selectByValue("3");
                break;
        }
        webDriver.findElement(By.xpath(".//button[@class='b-check_btn-send btn btn-primary']")).click();

        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    private QrCode parseQrCode(String qrCode) {
        QrCode code = new QrCode();
        qrCode = qrCode.replaceAll("&", "\n");
        ByteArrayInputStream stream = new ByteArrayInputStream(qrCode.getBytes());
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        code.setFd(properties.getProperty("i"));
        code.setFn(properties.getProperty("fn"));
        code.setFp(properties.getProperty("fp"));
        code.setS(properties.getProperty("s").replaceAll("\\.", ""));
        code.setN(properties.getProperty("n"));
        code.setT(properties.getProperty("t"));
        code.setQr("0");
        return code;
    }

    private HttpHeaders createHeaders(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        return headers;
    }

   /* private QrCode parseQrCode(String qrCode) {
        QrCode code = new QrCode();
        Map<String, String> map = new LinkedHashMap<>();
        String[] split = qrCode.split("&");
        for (String t : split) {
            String[] s1 = t.split("=");
            map.put(s1[0], s1[1]);
        }
        String t = map.get("t");
        String[] ts = t.split("T");
        char[] chars = new char[4];
        char[] chars1 = new char[4];
        ts[0].getChars(0, 4, chars, 0);
        ts[0].getChars(4, 8, chars1, 0);
        String dateTime = String.copyValueOf(chars1) + String.copyValueOf(chars) + ts[1];
        code.setFd(map.get("i"));
        code.setFn(map.get("fn"));
        code.setFp(map.get("fp"));
        code.setS(map.get("s"));
        code.setN(map.get("n"));
        code.setT(dateTime);
        code.setQr("0");
        return code;
    }*/

   /* private synchronized ChromeOptions getChromeOptions() {
        if (options == null) {
            options = new ChromeOptions();
            options.setProxy(getProxy());
            // options.addArguments("--user-data-dir $HOME"); //для linux хрома
            // options.addArguments("--no-sandbox");
            // options.addArguments("--headless");
            // options.addArguments("--disable-gpu");
        }
        return options;
    }

    private synchronized Proxy getProxy() {
        if (proxy == null) {
            proxy = new Proxy();
            proxy.setHttpProxy(seleniumProxy);
            proxy.setSslProxy(seleniumProxy);
        }
        return proxy;
    }*/
}
