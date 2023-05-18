package com.lemi.news.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {

    private Long id;
    private String description;
    private Instant dateCreate;
    private Instant dateUpdate;
    private String author;
}
