package com.technest.bank.service.impl;

import com.technest.bank.dao.AccountRepository;
import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.exception.AccountNotFoundException;
import com.technest.bank.service.AccountService;
import com.technest.bank.service.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private Mapper mapper;

  @Override
  public ResponseEntity<List<AccountDto>> findAll() {
    return ResponseEntity.ok()
        .body(accountRepository.findAll().stream().map(x -> mapper.mapToDto(x)).collect(
            Collectors.toList()));
  }

  @Override
  public ResponseEntity<AccountDto> findById(Integer id) throws AccountNotFoundException {
    if (id == null) {
      throw new IllegalArgumentException("Id can not be null");
    }

    return ResponseEntity.ok().body(accountRepository.findById(id).map(x -> mapper.mapToDto(x))
        .orElseThrow(
            () -> new AccountNotFoundException(String.format("Account with id %d not found", id))));
  }

  @Override
  public ResponseEntity<AccountDto> addAccount(AccountPostDto accountPostDto) {
    return null;
  }

  @Override
  public ResponseEntity<AccountDto> updateAccount(Integer id, AccountPostDto accountPostDto) {
    return null;
  }

  @Override
  public void deleteAccount(Integer id) {

  }
}
