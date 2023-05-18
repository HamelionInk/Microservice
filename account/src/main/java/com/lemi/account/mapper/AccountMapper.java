package com.lemi.account.mapper;

import com.lemi.account.dto.request.AccountRequestDto;
import com.lemi.account.dto.response.AccountResponseDto;
import com.lemi.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper{
    Account toEntity(AccountRequestDto dto);
    AccountResponseDto toDto(Account entity);
    Account toPatchEntity(AccountRequestDto dto, @MappingTarget Account entity);
}
