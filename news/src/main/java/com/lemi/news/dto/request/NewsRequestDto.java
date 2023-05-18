package com.lemi.news.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDto {

    private String description;

    @Builder.Default
    private Instant dateCreate = Instant.now();

    private Instant dateUpdate;
}
