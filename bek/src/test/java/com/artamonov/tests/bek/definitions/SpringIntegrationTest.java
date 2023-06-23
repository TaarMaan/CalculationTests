package com.artamonov.tests.bek.definitions;

import com.artamonov.tests.bek.BekApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = BekApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static HttpEntity<?> latestResponse = new HttpEntity<>(new HttpHeaders());
    @Autowired
    protected RestTemplate restTemplate;
    static int status;

    void executeGet(String url, String num1, String num2, int sys1, int sys2) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("num1", "{num1}")
                .queryParam("num2", "{num2}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        System.out.println(num1);
        System.out.println(num2);
        params.put("num1", String.valueOf(Integer.parseInt(num1, sys1)));
        params.put("num2", String.valueOf(Integer.parseInt(num2, sys2)));
        System.out.println("get params");
        System.out.println(params);
        ResponseEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        status = response.getStatusCode().value();
        latestResponse = response;
    }
}