package com.lemi.account.service;

import com.lemi.account.dto.request.AccountRequestDto;
import com.lemi.account.dto.response.AccountResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.List;

public interface AccountService {

    AccountResponseDto createAccount(@NonNull AccountRequestDto accountRequestDto);
    AccountResponseDto getAccount(@NonNull Long id);
    List<AccountResponseDto> getAccounts();
    AccountResponseDto updateAccount(@NotNull AccountRequestDto accountRequestDto, @NotNull Long id);
    void deleteAccount(@NotNull Long id);
}
