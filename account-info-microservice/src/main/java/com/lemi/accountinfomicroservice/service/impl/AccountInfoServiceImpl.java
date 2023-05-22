package com.lemi.accountinfomicroservice.service.impl;

import com.lemi.accountinfomicroservice.dto.request.AccountInfoRequest;
import com.lemi.accountinfomicroservice.dto.response.AccountInfoResponse;
import com.lemi.accountinfomicroservice.exception.NotFoundException;
import com.lemi.accountinfomicroservice.mapper.AccountInfoMapper;
import com.lemi.accountinfomicroservice.repository.AccountInfoRepository;
import com.lemi.accountinfomicroservice.service.AccountInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountInfoServiceImpl implements AccountInfoService {

    private static final String NOT_FOUND_ACCOUNT_INFO_MSG = "Аккаунт с id - %s не найден";

    private final AccountInfoRepository accountInfoRepository;
    private final AccountInfoMapper accountInfoMapper;

    @Override
    public AccountInfoResponse create(@NonNull AccountInfoRequest accountInfoRequest) {
        var accountInfo = accountInfoMapper.toEntity(accountInfoRequest);

        return accountInfoMapper.toDto(accountInfoRepository.save(accountInfo));
    }

    @Override
    public List<AccountInfoResponse> getAll() {
        return accountInfoRepository.findAll().stream()
                .map(accountInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountInfoResponse getById(@NonNull Long id) {
        return accountInfoRepository.findById(id)
                .map(accountInfoMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ACCOUNT_INFO_MSG, id)));
    }

    @Override
    public AccountInfoResponse update(@NonNull Long id, @NonNull AccountInfoRequest accountInfoRequest) {
        var accountInfoForUpdate = accountInfoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ACCOUNT_INFO_MSG, id)));

        var accountInfo = accountInfoMapper.toPatchEntity(accountInfoForUpdate, accountInfoRequest);

        return accountInfoMapper.toDto(accountInfoRepository.save(accountInfo));
    }

    @Override
    public void deleteById(@NonNull Long id) {
        accountInfoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ACCOUNT_INFO_MSG, id)));

        accountInfoRepository.deleteById(id);
    }
}
