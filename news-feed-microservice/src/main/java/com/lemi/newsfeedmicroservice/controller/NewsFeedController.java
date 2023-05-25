package com.lemi.newsfeedmicroservice.controller;

import com.lemi.newsfeedmicroservice.dto.request.NewsFeedRequestDto;
import com.lemi.newsfeedmicroservice.dto.response.NewsFeedResponseDto;
import com.lemi.newsfeedmicroservice.service.NewsFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/v1/news_feed")
public class NewsFeedController {

    private final NewsFeedService newsFeedService;

    @PostMapping
    public ResponseEntity<NewsFeedResponseDto> create(@RequestBody NewsFeedRequestDto newsFeedRequestDto) {
        var responseBody = newsFeedService.create(newsFeedRequestDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<NewsFeedResponseDto>> getAll() throws TimeoutException {
        var responseBody = newsFeedService.getAll();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsFeedResponseDto> getById(@PathVariable (name = "id") Long id) {
        var responseBody = newsFeedService.getById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NewsFeedResponseDto> update(@PathVariable (name = "id") Long id,
                                                      @RequestBody NewsFeedRequestDto newsFeedRequestDto) {
        var responseBody = newsFeedService.update(id, newsFeedRequestDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable (name = "id") Long id) {
        newsFeedService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
