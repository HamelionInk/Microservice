package com.lemi.newsfeedmicroservice.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoClientDto {

    private Long id;
    private String username;
    private String fullName;
    private Integer age;
    private Boolean isActive;
}
