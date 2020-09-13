package com.technest.bank.service;

import com.technest.bank.dto.AccountDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AccountService {

  ResponseEntity<List<AccountDto>> findAll();

  ResponseEntity<AccountDto> findById(Integer id);
}
