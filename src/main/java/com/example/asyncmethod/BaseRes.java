package com.example.asyncmethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRes<T> {

    private String code;

    private String msg;

    private String url;

    private T data;

    public static BaseRes of(String code, String msg, Object data) {
        return BaseRes.builder().code("00").msg("success").data(data).build();
    }
}
