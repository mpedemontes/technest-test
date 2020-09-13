package com.technest.bank.dto;

import javax.money.CurrencyUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

  private Integer id;
  private String name;
  private CurrencyUnit currency;
  private Money balance;
  private Boolean treasury;
}
