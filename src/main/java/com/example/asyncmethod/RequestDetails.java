package com.example.asyncmethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDetails {
    private String url;
    private HttpMethod requestType;
}
