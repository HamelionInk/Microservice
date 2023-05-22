package com.lemi.accountinfomicroservice.mapper;

import com.lemi.accountinfomicroservice.dto.request.AccountInfoRequest;
import com.lemi.accountinfomicroservice.dto.response.AccountInfoResponse;
import com.lemi.accountinfomicroservice.entity.AccountInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountInfoMapper {
    AccountInfo toEntity(AccountInfoRequest accountInfoRequest);
    AccountInfoResponse toDto(AccountInfo accountInfo);
    AccountInfo toPatchEntity(@MappingTarget AccountInfo accountInfo, AccountInfoRequest accountInfoRequest);
}
