package com.example.asyncmethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestSearchFee {
    private Long payType;
    private Boolean excel;
    private int fromRow;
    private int toRow;
}
