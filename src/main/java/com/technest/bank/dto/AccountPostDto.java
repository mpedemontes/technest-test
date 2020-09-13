package com.technest.bank.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPostDto {

  private String name;
  private String currency;
  private BigDecimal balance;
  private Boolean treasury;
}
