package com.technest.bank.service.impl;

import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.model.Account;
import com.technest.bank.service.Mapper;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl implements Mapper {

  @Override
  public AccountDto mapToDto(Account account) {
    return new AccountDto(
        account.getId(),
        account.getName(),
        account.getBalance().getCurrency(),
        account.getBalance(),
        account.getTreasury()
    );
  }

  @Override
  public Account mapToEntity(AccountPostDto accountPostDto) {
    CurrencyUnit currencyUnit = Monetary.getCurrency(accountPostDto.getCurrency());
    Money money = Money.of(accountPostDto.getBalance(), currencyUnit);
    return new Account(
        null,
        accountPostDto.getName(),
        money,
        accountPostDto.getTreasury()
    );
  }
}
