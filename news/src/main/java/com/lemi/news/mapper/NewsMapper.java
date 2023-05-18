package com.lemi.news.mapper;

import com.lemi.news.dto.request.NewsRequestDto;
import com.lemi.news.dto.response.NewsResponseDto;
import com.lemi.news.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper {
    News toEntity(NewsRequestDto dto);
    NewsResponseDto toDto(News entity);
    News toPatchEntity(NewsRequestDto dto, @MappingTarget News entity);
}
