package com.technest.bank.service;

import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.exception.AccountNotFoundException;
import com.technest.bank.exception.NegativeBalanceException;
import com.technest.bank.exception.TreasuryModifiedException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AccountService {

  ResponseEntity<List<AccountDto>> findAll();

  ResponseEntity<AccountDto> findById(Integer id) throws AccountNotFoundException;

  ResponseEntity<AccountDto> addAccount(AccountPostDto accountPostDto)
      throws NegativeBalanceException;

  ResponseEntity<AccountDto> updateAccount(Integer id, AccountPostDto accountPostDto)
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException;

  void deleteAccount(Integer id) throws AccountNotFoundException;

  ResponseEntity<AccountDto> transferMoney(Integer senderId, Integer receiverId, BigDecimal amount,
      String currency) throws AccountNotFoundException, NegativeBalanceException;

  ResponseEntity<AccountDto> updateAccountCurrency(Integer id, String currency)
      throws AccountNotFoundException;
}
