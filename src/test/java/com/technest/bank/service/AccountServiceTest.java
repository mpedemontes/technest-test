package com.technest.bank.service;

import com.technest.bank.dao.AccountRepository;
import com.technest.bank.dto.AccountDto;
import com.technest.bank.exception.AccountNotFoundException;
import com.technest.bank.model.Account;
import com.technest.bank.service.impl.AccountServiceImpl;
import com.technest.bank.service.impl.MapperImpl;
import java.util.ArrayList;
import java.util.List;
import javax.money.Monetary;
import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  @InjectMocks
  private AccountServiceImpl accountService;

  @Mock
  private AccountRepository accountRepository;

  @Spy
  private MapperImpl mapper;

  @Test
  public void findAllTest() {
    // Given
    AccountDto accountDto1 = new AccountDto(1, "Account1", Monetary.getCurrency("EUR"), Money
        .of(50, "EUR"), false);
    AccountDto accountDto2 = new AccountDto(2, "Account2", Monetary.getCurrency("USD"), Money
        .of(100, "USD"), false);
    List<AccountDto> accountsDto = new ArrayList<>();
    accountsDto.add(accountDto1);
    accountsDto.add(accountDto2);

    Account account1 = new Account(1, "Account1", Money.of(50, "EUR"), false);
    Account account2 = new Account(2, "Account2", Money.of(100, "USD"), false);
    List<Account> accounts = new ArrayList<>();
    accounts.add(account1);
    accounts.add(account2);

    // When
    Mockito.when(accountRepository.findAll()).thenReturn(accounts);

    // Then
    ResponseEntity<List<AccountDto>> result = accountService.findAll();
    Assert.assertNotNull(result);
    Assert.assertEquals(result, ResponseEntity.ok().body(accountsDto));
  }

  @Test(expected = IllegalArgumentException.class)
  public void findByIdWithNullArgumentTest() throws AccountNotFoundException {
    // Given
    final Integer id = null;

    // When

    // Then
    accountService.findById(id);
  }

  @Test(expected = AccountNotFoundException.class)
  public void findByIdWithNonExistingIdTest() throws AccountNotFoundException {
    // Given
    final Integer id = 1;

    // When

    // Then
    accountService.findById(id);
  }

  @Test
  public void findByIdWithExistingIdTest() throws AccountNotFoundException {
    // Given
    final Integer id = 1;
    Account account = new Account(1, "Account1", Money.of(50, "EUR"), false);
    AccountDto accountDto = new AccountDto(1, "Account1", Monetary.getCurrency("EUR"),
        Money.of(50, "EUR"), false);

    // When
    Mockito.when(accountRepository.findById(id)).thenReturn(java.util.Optional.of(account));

    // Then
    ResponseEntity<AccountDto> result = accountService.findById(id);
    Assert.assertNotNull(result);
    Assert.assertEquals(result, ResponseEntity.ok().body(accountDto));
  }
}
