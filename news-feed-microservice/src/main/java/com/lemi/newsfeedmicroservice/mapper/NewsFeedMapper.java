package com.lemi.newsfeedmicroservice.mapper;

import com.lemi.newsfeedmicroservice.dto.request.NewsFeedRequestDto;
import com.lemi.newsfeedmicroservice.dto.response.NewsFeedResponseDto;
import com.lemi.newsfeedmicroservice.entity.NewsFeed;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsFeedMapper {
    NewsFeed toEntity(NewsFeedRequestDto newsFeedRequestDto);
    NewsFeedResponseDto toDto(NewsFeed newsFeed);
    NewsFeed toPatchEntity (@MappingTarget NewsFeed newsFeed, NewsFeedRequestDto newsFeedRequestDto);
}
