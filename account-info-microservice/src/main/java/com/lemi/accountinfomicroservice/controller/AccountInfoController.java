package com.lemi.accountinfomicroservice.controller;

import com.lemi.accountinfomicroservice.dto.request.AccountInfoRequest;
import com.lemi.accountinfomicroservice.dto.response.AccountInfoResponse;
import com.lemi.accountinfomicroservice.service.AccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/v1/account_info")
public class AccountInfoController {

    private final AccountInfoService accountInfoService;

    @PostMapping
    public ResponseEntity<AccountInfoResponse> create(@RequestBody AccountInfoRequest accountInfoRequest) {
        var responseBody = accountInfoService.create(accountInfoRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountInfoResponse> getById(@PathVariable (name = "id") Long id) {
        var responseBody = accountInfoService.getById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<AccountInfoResponse>> getAll() {
        var responseBody = accountInfoService.getAll();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountInfoResponse> update(@PathVariable (name = "id") Long id,
                                                      @RequestBody AccountInfoRequest accountInfoRequest) {
        var responseBody = accountInfoService.update(id, accountInfoRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    public ResponseEntity<?> deleteById(@PathVariable (name = "id") Long id) {
       accountInfoService.deleteById(id);
       return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
