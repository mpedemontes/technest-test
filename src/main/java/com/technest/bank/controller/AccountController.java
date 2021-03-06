package com.technest.bank.controller;

import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.exception.AccountNotFoundException;
import com.technest.bank.exception.NegativeBalanceException;
import com.technest.bank.exception.TreasuryModifiedException;
import com.technest.bank.service.AccountService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<AccountDto>> findAll() {
    return accountService.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<AccountDto> findById(@PathVariable("id") Integer id)
      throws AccountNotFoundException {
    return accountService.findById(id);
  }

  @GetMapping("/{senderId}/transfer/{receiverId}/{amount}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<AccountDto> transferMoney(@PathVariable("senderId") Integer senderId,
      @PathVariable("receiverId") Integer receiverId, @PathVariable("amount")
      BigDecimal amount, @RequestParam(name = "currency", required = false) String currency)
      throws AccountNotFoundException, NegativeBalanceException {
    return accountService.transferMoney(senderId, receiverId, amount, currency);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<AccountDto> addAccount(@RequestBody AccountPostDto accountPostDto)
      throws NegativeBalanceException {
    return accountService.addAccount(accountPostDto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Integer id,
      @RequestBody AccountPostDto accountPostDto)
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    return accountService.updateAccount(id, accountPostDto);
  }

  @PutMapping("/{id}/currency/{currency}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<AccountDto> updateAccountCurrency(@PathVariable("id") Integer id,
      @PathVariable("currency") String currency) throws AccountNotFoundException {
    return accountService.updateAccountCurrency(id, currency);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAccount(@PathVariable("id") Integer id) throws AccountNotFoundException {
    accountService.deleteAccount(id);
  }
}
