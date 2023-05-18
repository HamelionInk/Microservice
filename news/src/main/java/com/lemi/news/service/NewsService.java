package com.lemi.news.service;

import com.lemi.news.dto.request.NewsRequestDto;
import com.lemi.news.dto.response.NewsResponseDto;
import lombok.NonNull;

import java.util.List;

public interface NewsService {

    NewsResponseDto create(@NonNull NewsRequestDto newsRequestDto);
    NewsResponseDto getNews(@NonNull Long id);
    List<NewsResponseDto> getNews();
    NewsResponseDto update(@NonNull Long id, @NonNull NewsRequestDto newsRequestDto);
    void delete(@NonNull Long id);
}
