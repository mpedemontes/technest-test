package com.technest.bank.service;

import com.technest.bank.dao.AccountRepository;
import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.exception.AccountNotFoundException;
import com.technest.bank.exception.NegativeBalanceException;
import com.technest.bank.exception.TreasuryModifiedException;
import com.technest.bank.model.Account;
import com.technest.bank.service.impl.AccountServiceImpl;
import com.technest.bank.service.impl.MapperImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.money.Monetary;
import javax.money.UnknownCurrencyException;
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
    Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(account));

    // Then
    ResponseEntity<AccountDto> result = accountService.findById(id);
    Assert.assertNotNull(result);
    Assert.assertEquals(result, ResponseEntity.ok().body(accountDto));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAccountWithNullNameTest() throws NegativeBalanceException {
    // Given
    AccountPostDto accountPostDto = new AccountPostDto(null, "EUR", BigDecimal.valueOf(50), false);

    // When

    // Then
    accountService.addAccount(accountPostDto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAccountWithNullCurrencyTest() throws NegativeBalanceException {
    // Given
    AccountPostDto accountPostDto = new AccountPostDto("Account1", null, BigDecimal.valueOf(50),
        false);

    // When

    // Then
    accountService.addAccount(accountPostDto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAccountWithNullBalanceTest() throws NegativeBalanceException {
    // Given
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", null, false);

    // When

    // Then
    accountService.addAccount(accountPostDto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAccountWithNullTreasuryTest() throws NegativeBalanceException {
    // Given
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(50),
        null);

    // When

    // Then
    accountService.addAccount(accountPostDto);
  }

  @Test(expected = NegativeBalanceException.class)
  public void addNonTreasuryAccountWithNullBalanceTest() throws NegativeBalanceException {
    // Given
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(-10),
        false);

    // When

    // Then
    accountService.addAccount(accountPostDto);
  }

  @Test
  public void addTreasuryAccountWithNegativeBalanceTest() throws NegativeBalanceException {
    // Given
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(-10),
        true);
    Account account = new Account(1, "Account", Money.of(-10, "EUR"), true);
    AccountDto accountDto = new AccountDto(1, "Account", Monetary.getCurrency("EUR"),
        Money.of(-10, "EUR"), true);

    // When
    Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account);

    // Then
    ResponseEntity<AccountDto> result = accountService.addAccount(accountPostDto);
    Assert.assertNotNull(result);
    Assert.assertEquals(result, ResponseEntity.ok().body(accountDto));
  }

  @Test
  public void addNonTreasuryAccountWithNonNegativeBalanceTest() throws NegativeBalanceException {
    // Given
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(50),
        false);
    Account account = new Account(1, "Account", Money.of(50, "EUR"), false);
    AccountDto accountDto = new AccountDto(1, "Account", Monetary.getCurrency("EUR"),
        Money.of(50, "EUR"), false);

    // When
    Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account);

    // Then
    ResponseEntity<AccountDto> result = accountService.addAccount(accountPostDto);
    Assert.assertNotNull(result);
    Assert.assertEquals(result, ResponseEntity.ok().body(accountDto));
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateAccountWithNullIdTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = null;
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(50),
        false);

    // When

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateAccountWithNullNameTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto(null, "EUR", BigDecimal.valueOf(50), false);

    // When

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateAccountWithNullCurrencyTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto("Account", null, BigDecimal.valueOf(50),
        false);

    // When

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateAccountWithNullBalanceTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", null, false);

    // When

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateAccountWithNullTreasuryTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(50),
        null);

    // When

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = AccountNotFoundException.class)
  public void updateNonExistingAccountTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(50),
        false);

    // When

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = TreasuryModifiedException.class)
  public void updateAccountModifyingTreasuryTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    Account account = new Account(id, "Account", Money.of(50, "EUR"), false);
    AccountPostDto accountPostDto = new AccountPostDto("Account", "EUR", BigDecimal.valueOf(50),
        true);

    // When
    Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(
        Optional.of(account));

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = NegativeBalanceException.class)
  public void updateNonTreasuryAccountToNegativeBalanceTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto("Account1", "EUR", BigDecimal.valueOf(-50),
        false);
    Account account = new Account(id, "Account1", Money.of(50, "EUR"), false);

    // When
    Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(account));

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test(expected = UnknownCurrencyException.class)
  public void updateAccountWithInvalidCurrencyTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto("Account1", "ABC", BigDecimal.valueOf(50),
        false);
    Account account = new Account(id, "Account1", Money.of(50, "EUR"), false);

    // When
    Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(
        Optional.of(account));

    // Then
    accountService.updateAccount(id, accountPostDto);
  }

  @Test
  public void updateAccountWithValidDataTest()
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    // Given
    final Integer id = 1;
    AccountPostDto accountPostDto = new AccountPostDto("New Name", "EUR", BigDecimal.valueOf(50),
        false);
    Account oldAccount = new Account(id, "Old Name", Money.of(50, "EUR"), false);
    Account newAccount = new Account(id, "New Name", Money.of(50, "EUR"), false);
    AccountDto newAccountDto = new AccountDto(id, "New Name", Monetary.getCurrency("EUR"),
        Money.of(50, "EUR"), false);

    // When
    Mockito.when(accountRepository.findById(id)).thenReturn(
        Optional.of(oldAccount));
    Mockito.when(accountRepository.save(newAccount)).thenReturn(newAccount);

    // Then
    ResponseEntity<AccountDto> result = accountService.updateAccount(id, accountPostDto);
    Assert.assertNotNull(result);
    Assert.assertEquals(result, ResponseEntity.ok().body(newAccountDto));
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteAccountWithNullIdTest() throws AccountNotFoundException {
    // Given

    // When

    // Then
    accountService.deleteAccount(null);
  }

  @Test(expected = AccountNotFoundException.class)
  public void deleteNonExistingAccountTest() throws AccountNotFoundException {
    // Given
    final Integer id = 1;

    // When

    // Then
    accountService.deleteAccount(id);
  }

  @Test
  public void deleteAccountTest() throws AccountNotFoundException {
    // Given
    final Integer id = 1;
    Account account = new Account(id, "Account1", Money.of(50, "EUR"), false);

    // When
    Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(account));

    // Then
    accountService.deleteAccount(id);
    Mockito.verify(accountRepository).delete(account);
  }
}
