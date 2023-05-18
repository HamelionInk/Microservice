package com.lemi.news.controller;

import com.lemi.news.dto.request.NewsRequestDto;
import com.lemi.news.dto.response.NewsResponseDto;
import com.lemi.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsResponseDto> create(@RequestBody NewsRequestDto dto) {
        var responseBody = newsService.create(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping("/get")
    public String test() {
        return "Work";
    }
}
