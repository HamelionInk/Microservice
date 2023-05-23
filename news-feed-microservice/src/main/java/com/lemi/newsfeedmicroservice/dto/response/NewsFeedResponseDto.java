package com.lemi.newsfeedmicroservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsFeedResponseDto {

    private Long id;
    private String title;
    private String description;
    private String fullName;

}
