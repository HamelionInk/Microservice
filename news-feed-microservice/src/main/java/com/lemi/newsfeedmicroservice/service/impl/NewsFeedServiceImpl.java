package com.lemi.newsfeedmicroservice.service.impl;

import com.lemi.newsfeedmicroservice.client.AccountInfoClientApi;
import com.lemi.newsfeedmicroservice.dto.request.NewsFeedRequestDto;
import com.lemi.newsfeedmicroservice.dto.response.NewsFeedResponseDto;
import com.lemi.newsfeedmicroservice.exception.NotFoundException;
import com.lemi.newsfeedmicroservice.mapper.NewsFeedMapper;
import com.lemi.newsfeedmicroservice.repository.NewsFeedRepository;
import com.lemi.newsfeedmicroservice.service.NewsFeedService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsFeedServiceImpl implements NewsFeedService {

    private static final String NOT_FOUND_NEWS_FEED_MSG = "Запись с id - %s не найдена";

    private final NewsFeedRepository newsFeedRepository;
    private final NewsFeedMapper newsFeedMapper;
    private final AccountInfoClientApi accountInfoClientApi;

    @Override
    public NewsFeedResponseDto create(@NonNull NewsFeedRequestDto newsFeedRequestDto) {
        var newsFeed = newsFeedMapper.toEntity(newsFeedRequestDto);

        var accountInfo = accountInfoClientApi.getById(2L);

        newsFeed.setFullName(accountInfo.getFullName());

        return newsFeedMapper.toDto(newsFeedRepository.save(newsFeed));
    }

    @CircuitBreaker(name = "newsFeedService", fallbackMethod = "buildFallBackGetAll")
    //@Bulkhead(name = "bulkheadNewsFeedService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "buildFallBackGetAll")
    @Retry(name = "retryNewsFeedService", fallbackMethod = "buildFallBackGetAll")
    @Override
    public List<NewsFeedResponseDto> getAll() throws TimeoutException {
        randomlyRunLong();
        return newsFeedRepository.findAll().stream()
                .map(newsFeedMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<NewsFeedResponseDto> buildFallBackGetAll(Throwable t) {
        var fallBack = new ArrayList<NewsFeedResponseDto>();

        var newsFeedResponseDto = NewsFeedResponseDto.builder()
                .title("Title not acceptable")
                .description("Service not available")
                .fullName("unknown info")
                .build();

        fallBack.add(newsFeedResponseDto);

        return fallBack;
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

    private void randomlyRunLong() throws TimeoutException {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        if (randomNumber==2) {
            sleep();
        }
    }

    private void sleep() throws TimeoutException {
        try {
            Thread.sleep(2000);
            throw new TimeoutException();
        } catch (InterruptedException exception) {
            log.error(exception.getMessage());
        }
    }
}
