package com.lemi.newsfeedmicroservice.service;

import com.lemi.newsfeedmicroservice.dto.request.NewsFeedRequestDto;
import com.lemi.newsfeedmicroservice.dto.response.NewsFeedResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface NewsFeedService {

    NewsFeedResponseDto create(@NonNull NewsFeedRequestDto newsFeedRequestDto);
    List<NewsFeedResponseDto> getAll() throws TimeoutException;
    NewsFeedResponseDto getById(@NonNull Long id);
    NewsFeedResponseDto update(@NonNull Long id, @NonNull NewsFeedRequestDto newsFeedRequestDto);
    void deleteById(@NonNull Long id);
}
