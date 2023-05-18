package com.lemi.news.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String status;
    private Instant lastLoginTime;
    private Integer age;
    private Boolean accountState;
}
