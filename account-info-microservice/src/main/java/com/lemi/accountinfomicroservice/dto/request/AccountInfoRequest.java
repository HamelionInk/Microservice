package com.lemi.accountinfomicroservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoRequest {

    private String username;
    private String fullName;
    private Integer age;
    private Boolean isActive;
}
