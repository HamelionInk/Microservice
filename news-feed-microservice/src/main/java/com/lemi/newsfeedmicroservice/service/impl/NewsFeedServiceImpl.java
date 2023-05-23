package com.lemi.newsfeedmicroservice.service.impl;

import com.lemi.newsfeedmicroservice.dto.request.NewsFeedRequestDto;
import com.lemi.newsfeedmicroservice.dto.response.NewsFeedResponseDto;
import com.lemi.newsfeedmicroservice.exception.NotFoundException;
import com.lemi.newsfeedmicroservice.mapper.NewsFeedMapper;
import com.lemi.newsfeedmicroservice.repository.NewsFeedRepository;
import com.lemi.newsfeedmicroservice.service.NewsFeedService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsFeedServiceImpl implements NewsFeedService {

    private static final String NOT_FOUND_NEWS_FEED_MSG = "Запись с id - %s не найдена";

    private final NewsFeedRepository newsFeedRepository;
    private final NewsFeedMapper newsFeedMapper;

    @Override
    public NewsFeedResponseDto create(@NonNull NewsFeedRequestDto newsFeedRequestDto) {
        var newsFeed = newsFeedMapper.toEntity(newsFeedRequestDto);

        return newsFeedMapper.toDto(newsFeedRepository.save(newsFeed));
    }

    @Override
    public List<NewsFeedResponseDto> getAll() {
        return newsFeedRepository.findAll().stream()
                .map(newsFeedMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsFeedResponseDto getById(@NonNull Long id) {
        return newsFeedRepository.findById(id)
                .map(newsFeedMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_NEWS_FEED_MSG, id)));
    }

    @Override
    public NewsFeedResponseDto update(@NonNull Long id, @NonNull NewsFeedRequestDto newsFeedRequestDto) {
        var newsFeedForUpdate = newsFeedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_NEWS_FEED_MSG, id)));

        var newsFeed = newsFeedMapper.toPatchEntity(newsFeedForUpdate, newsFeedRequestDto);

        return newsFeedMapper.toDto(newsFeedRepository.save(newsFeed));
    }

    @Override
    public void deleteById(@NonNull Long id) {
        newsFeedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_NEWS_FEED_MSG, id)));

        newsFeedRepository.deleteById(id);
    }
}
