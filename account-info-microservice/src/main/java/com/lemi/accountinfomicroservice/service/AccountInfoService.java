package com.lemi.accountinfomicroservice.service;

import com.lemi.accountinfomicroservice.dto.request.AccountInfoRequest;
import com.lemi.accountinfomicroservice.dto.response.AccountInfoResponse;
import lombok.NonNull;

import java.util.List;

public interface AccountInfoService {
    AccountInfoResponse create(@NonNull AccountInfoRequest accountInfoRequest);
    List<AccountInfoResponse> getAll();
    AccountInfoResponse getById(@NonNull Long id);
    AccountInfoResponse update(@NonNull Long id, @NonNull AccountInfoRequest accountInfoRequest);
    void deleteById(@NonNull Long id);
}
