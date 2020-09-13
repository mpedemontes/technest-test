package com.technest.bank.service.impl;

import com.technest.bank.dao.AccountRepository;
import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.exception.AccountNotFoundException;
import com.technest.bank.exception.NegativeBalanceException;
import com.technest.bank.exception.TreasuryModifiedException;
import com.technest.bank.model.Account;
import com.technest.bank.service.AccountService;
import com.technest.bank.service.Mapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import org.javamoney.moneta.Money;
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
  public ResponseEntity<AccountDto> addAccount(AccountPostDto accountPostDto)
      throws NegativeBalanceException {
    if (accountPostDto.getName() == null) {
      throw new IllegalArgumentException("Name can not be null");
    }
    if (accountPostDto.getBalance() == null) {
      throw new IllegalArgumentException("Balance can not be null");
    }
    if (accountPostDto.getCurrency() == null) {
      throw new IllegalArgumentException("Currency can not be null");
    }
    if (accountPostDto.getTreasury() == null) {
      throw new IllegalArgumentException("Treasury can not be null");
    }

    // Check if starting balance is negative
    if (accountPostDto.getBalance().compareTo(BigDecimal.ZERO) < 0 && Boolean.FALSE
        .equals(accountPostDto.getTreasury())) {
      throw new NegativeBalanceException("Only treasury accounts can have a negative balance");
    }

    return ResponseEntity.ok()
        .body(mapper.mapToDto(accountRepository.save(mapper.mapToEntity(accountPostDto))));
  }

  @Override
  public ResponseEntity<AccountDto> updateAccount(Integer id, AccountPostDto accountPostDto)
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException {
    if (id == null) {
      throw new IllegalArgumentException("Id can not be null");
    }
    if (accountPostDto.getName() == null) {
      throw new IllegalArgumentException("Name can not be null");
    }
    if (accountPostDto.getBalance() == null) {
      throw new IllegalArgumentException("Balance can not be null");
    }
    if (accountPostDto.getCurrency() == null) {
      throw new IllegalArgumentException("Currency can not be null");
    }
    if (accountPostDto.getTreasury() == null) {
      throw new IllegalArgumentException("Treasury can not be null");
    }

    Account account = accountRepository.findById(id).orElseThrow(
        () -> new AccountNotFoundException(String.format("Account with id %d not found", id)));

    // Check if treasury property has been modified
    if (!account.getTreasury().equals(accountPostDto.getTreasury())) {
      throw new TreasuryModifiedException("Treasury property can not be modified");
    }

    // Check if new balance is negative and treasury property
    if (accountPostDto.getBalance().compareTo(BigDecimal.ZERO) < 0 && Boolean.FALSE
        .equals(accountPostDto.getTreasury())) {
      throw new NegativeBalanceException("Only treasury accounts can have a negative balance");
    }

    account = mapper.mapToEntity(accountPostDto);
    account.setId(id);
    return ResponseEntity.ok().body(mapper.mapToDto(accountRepository.save(account)));
  }

  @Override
  public void deleteAccount(Integer id) throws AccountNotFoundException {
    if (id == null) {
      throw new IllegalArgumentException("Id can not be null");
    }
    Account account = accountRepository.findById(id).orElseThrow(
        () -> new AccountNotFoundException(String.format("Account with id %d not found", id)));
    accountRepository.delete(account);
  }

  @Override
  public ResponseEntity<AccountDto> transferMoney(Integer senderId, Integer receiverId,
      BigDecimal amount, String currency)
      throws AccountNotFoundException, NegativeBalanceException {
    if (senderId == null) {
      throw new IllegalArgumentException("Id can not be null");
    }
    if (receiverId == null) {
      throw new IllegalArgumentException("Id can not be null");
    }
    if (amount == null) {
      throw new IllegalArgumentException("Amount can not be null");
    }
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount to transfer must be greater than 0");
    }

    if (senderId.equals(receiverId)) {
      throw new IllegalArgumentException("Sender and receiver must be different accounts");
    }

    Account sender = accountRepository.findById(senderId).orElseThrow(
        () -> new AccountNotFoundException(
            String.format("Account with id %d not found", senderId)));
    Account receiver = accountRepository.findById(receiverId).orElseThrow(
        () -> new AccountNotFoundException(
            String.format("Account with id %d not found", senderId)));

    CurrencyUnit transferCurrency =
        currency == null ? sender.getBalance().getCurrency() : Monetary.getCurrency(currency);
    CurrencyConversion transferConversion = MonetaryConversions.getConversion(transferCurrency);
    CurrencyConversion senderConversion = MonetaryConversions
        .getConversion(sender.getBalance().getCurrency());
    CurrencyConversion receiverConversion = MonetaryConversions
        .getConversion(receiver.getBalance().getCurrency());
    Money transfer = Money.of(amount, transferCurrency);

    // Check if sender has enough funds to transfer (if not treasury)
    if (sender.getBalance().with(transferConversion).subtract(transfer).isNegative()
        && Boolean.FALSE.equals(sender.getTreasury())) {
      throw new NegativeBalanceException(String.format(
          "Account with id %d does not have enough funds. Only treasury accounts can have a negative balance",
          senderId));
    }

    // Subtract transfer from sender account and restore its currency
    sender.setBalance(
        sender.getBalance().with(transferConversion).subtract(transfer).with(senderConversion));

    // Add transfer to receiver account and restore its currency
    receiver.setBalance(
        receiver.getBalance().with(transferConversion).add(transfer).with(receiverConversion));

    // Save both accounts and return sender account
    accountRepository.save(receiver);
    return ResponseEntity.ok().body(mapper.mapToDto(accountRepository.save(sender)));
  }

  @Override
  public ResponseEntity<AccountDto> updateAccountCurrency(Integer id, String currency)
      throws AccountNotFoundException {
    if (id == null) {
      throw new IllegalArgumentException("Id can not be null");
    }
    if (currency == null) {
      throw new IllegalArgumentException("Currency can not be null");
    }

    CurrencyUnit newCurrency = Monetary.getCurrency(currency);

    Account account = accountRepository.findById(id).orElseThrow(
        () -> new AccountNotFoundException(String.format("Account with id %d not found", id)));

    Money oldBalance = account.getBalance();
    CurrencyConversion conversion = MonetaryConversions.getConversion(newCurrency);
    Money newBalance = oldBalance.with(conversion);

    account.setBalance(newBalance);
    return ResponseEntity.ok().body(mapper.mapToDto(accountRepository.save(account)));
  }

}
