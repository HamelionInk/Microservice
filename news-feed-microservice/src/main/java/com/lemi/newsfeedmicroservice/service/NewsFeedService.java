package com.lemi.newsfeedmicroservice.service;

import com.lemi.newsfeedmicroservice.dto.request.NewsFeedRequestDto;
import com.lemi.newsfeedmicroservice.dto.response.NewsFeedResponseDto;
import lombok.NonNull;

import java.util.List;

public interface NewsFeedService {

    NewsFeedResponseDto create(@NonNull NewsFeedRequestDto newsFeedRequestDto);
    List<NewsFeedResponseDto> getAll();
    NewsFeedResponseDto getById(@NonNull Long id);
    NewsFeedResponseDto update(@NonNull Long id, @NonNull NewsFeedRequestDto newsFeedRequestDto);
    void deleteById(@NonNull Long id);
}
