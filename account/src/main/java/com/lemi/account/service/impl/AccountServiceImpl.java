package com.lemi.account.service.impl;

import com.lemi.account.dto.request.AccountRequestDto;
import com.lemi.account.dto.response.AccountResponseDto;
import com.lemi.account.exception.NotFoundException;
import com.lemi.account.mapper.AccountMapper;
import com.lemi.account.repository.AccountRepository;
import com.lemi.account.service.AccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Аккаунт с id = %s не найден";

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public AccountResponseDto createAccount(@NonNull AccountRequestDto accountRequestDto) {
        var account = accountMapper.toEntity(accountRequestDto);

        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountResponseDto getAccount(@NonNull Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_NOT_FOUND_MESSAGE, id)));
    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDto updateAccount(AccountRequestDto accountRequestDto, Long id) {
        var accountForUpdate = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_NOT_FOUND_MESSAGE, id)));

        var account = accountMapper.toPatchEntity(accountRequestDto, accountForUpdate);

        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_NOT_FOUND_MESSAGE, id)));

        accountRepository.deleteById(id);
    }
}
