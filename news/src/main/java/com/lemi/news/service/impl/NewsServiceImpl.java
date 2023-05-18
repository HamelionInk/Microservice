package com.lemi.news.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemi.news.dto.request.NewsRequestDto;
import com.lemi.news.dto.response.AccountResponseDto;
import com.lemi.news.dto.response.NewsResponseDto;
import com.lemi.news.mapper.NewsMapper;
import com.lemi.news.repository.NewsRepository;
import com.lemi.news.service.NewsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;
    private final NewsRepository newsRepository;

    @Override
    public NewsResponseDto create(@NonNull NewsRequestDto newsRequestDto) {
        var news = newsMapper.toEntity(newsRequestDto);
        news.setAuthor(getAuthor());

        return newsMapper.toDto(newsRepository.save(news));
    }

    @Override
    public NewsResponseDto getNews(@NonNull Long id) {
        return null;
    }

    @Override
    public List<NewsResponseDto> getNews() {
        return null;
    }

    @Override
    public NewsResponseDto update(@NonNull Long id, @NonNull NewsRequestDto newsRequestDto) {
        return null;
    }

    @Override
    public void delete(@NonNull Long id) {

    }

    private String getAuthor() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        var response = restTemplate.exchange("http://localhost:8080/accounts/1",
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        Object.class)
                .getBody();

        return objectMapper.convertValue(response, AccountResponseDto.class).getFirstname();
    }
}
