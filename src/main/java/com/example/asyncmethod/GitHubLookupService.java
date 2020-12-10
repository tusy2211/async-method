package com.example.asyncmethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GitHubLookupService {

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<User> findUser(Long payType) {
        logger.info("Looking up " + payType);
        String url = "http://10.22.7.38:8077/fee/all-jpa";
        RequestSearchFee requestSearchFee = RequestSearchFee.builder()
                .excel(false)
                .payType(payType)
                .fromRow(0)
                .toRow(10)
                .build();
        String request = null;
        try {
            request = objectMapper.writeValueAsString(requestSearchFee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String responseBody = new GenericRestClient<String,String>().execute(restTemplate, url, request,
                String.class);
        assert responseBody != null;
        BaseRes userList = null;
        try {
            userList = objectMapper.readValue(responseBody, BaseRes.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        List<User> user = (List<User>) userList.getData();
        // Artificial delay of 1s for demonstration purposes
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(user.get(0));
    }

    String findUser1(Long payType) {
        try {
            logger.info("Looking up " + payType);
            String url = "http://10.22.7.38:8077/fee/all-jpa";
            RequestSearchFee requestSearchFee = RequestSearchFee.builder()
                    .excel(false)
                    .payType(payType)
                    .fromRow(0)
                    .toRow(10)
                    .build();
            String request = objectMapper.writeValueAsString(requestSearchFee);
            String responseBody = new GenericRestClient<String,String>().execute(restTemplate, url, request,
                    String.class);
            assert responseBody != null;
            BaseRes userList = objectMapper.readValue(responseBody, BaseRes.class);
            List<User> user = (List<User>) userList.getData();
            return objectMapper.writeValueAsString(user.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return Strings.EMPTY;
        }
    }

}
