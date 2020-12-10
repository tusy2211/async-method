package com.example.asyncmethod;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenericRestClient<T,V> {

    V execute(RestTemplate restTemplate, String url, T data, Class<V> genericClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<T>(data, headers);
        ResponseEntity<V> response = restTemplate.exchange(url, HttpMethod.POST, entity,  genericClass);
        return response.getBody();
    }
}
