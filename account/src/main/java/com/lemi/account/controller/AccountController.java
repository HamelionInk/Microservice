package com.lemi.account.controller;

import com.lemi.account.dto.request.AccountRequestDto;
import com.lemi.account.dto.response.AccountResponseDto;
import com.lemi.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping()
    public ResponseEntity<AccountResponseDto> create(@RequestBody AccountRequestDto dto) {
        log.info("POST: /account dto={}", dto);

        var responseBody = accountService.createAccount(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponseDto>> getAll() {
        log.info("GET: /account");

        var responseBody = accountService.getAccounts();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> get(@PathVariable (name = "id") Long id) {
        log.info("GET: /account/{id} id={}", id);

        var responseBody = accountService.getAccount(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponseDto> update(@PathVariable (name = "id") Long id,
                                                     @RequestBody AccountRequestDto dto) {
        log.info("PATCH: /account/{id} id={}, dto={}", id, dto);

        var responseBody = accountService.updateAccount(dto, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable (name = "id") Long id) {
        log.info("DELETE: /account/{id} id={}", id);

        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
