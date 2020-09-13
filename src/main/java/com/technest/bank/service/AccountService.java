package com.technest.bank.service;

import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.exception.AccountNotFoundException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AccountService {

  ResponseEntity<List<AccountDto>> findAll();

  ResponseEntity<AccountDto> findById(Integer id) throws AccountNotFoundException;

  ResponseEntity<AccountDto> addAccount(AccountPostDto accountPostDto);

  ResponseEntity<AccountDto> updateAccount(Integer id, AccountPostDto accountPostDto);

  void deleteAccount(Integer id);
}
