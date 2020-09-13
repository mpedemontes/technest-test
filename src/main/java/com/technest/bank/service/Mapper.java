package com.technest.bank.service;

import com.technest.bank.dto.AccountDto;
import com.technest.bank.dto.AccountPostDto;
import com.technest.bank.model.Account;

public interface Mapper {

  AccountDto mapToDto(Account account);

  Account mapToEntity(AccountPostDto accountPostDto);
}
