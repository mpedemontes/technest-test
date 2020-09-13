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

  /**
   * Lists all accounts in database
   *
   * @return list containing all accounts
   */
  ResponseEntity<List<AccountDto>> findAll();

  /**
   * Shows an account with a given id
   *
   * @param id identifier of the account to find
   * @return the account with given id
   * @throws AccountNotFoundException if no account is found with given id
   */
  ResponseEntity<AccountDto> findById(Integer id) throws AccountNotFoundException;

  /**
   * Adds a new account to the database
   *
   * @param accountPostDto the account to add
   * @return the saved account
   * @throws NegativeBalanceException if balance is negative and account is not treasury
   */
  ResponseEntity<AccountDto> addAccount(AccountPostDto accountPostDto)
      throws NegativeBalanceException;

  /**
   * Updates an account
   *
   * @param id             identifier of the account to update
   * @param accountPostDto the account with modified values
   * @return the updates account
   * @throws AccountNotFoundException  if no account is found with the given id
   * @throws TreasuryModifiedException if treasury property is modified
   * @throws NegativeBalanceException  if new balance is negative and account is not treasury
   */
  ResponseEntity<AccountDto> updateAccount(Integer id, AccountPostDto accountPostDto)
      throws AccountNotFoundException, TreasuryModifiedException, NegativeBalanceException;

  /**
   * Deletes an account
   *
   * @param id identifier of the account to delete
   * @throws AccountNotFoundException if no account is found with the given id
   */
  void deleteAccount(Integer id) throws AccountNotFoundException;

  /**
   * Transfers money from one account to another one
   *
   * @param senderId   identifier of the account which is sending money
   * @param receiverId identifier of the account which is receiving money
   * @param amount     amount of money to transfer
   * @param currency   currency used for the transfer. If not indicated, sender's account currency
   *                   is taken
   * @return the sender account once the transfer is done
   * @throws AccountNotFoundException if no account is found with the given ids
   * @throws NegativeBalanceException if sender account is not treasury and tries to send an amount
   *                                  higher than its balance
   */
  ResponseEntity<AccountDto> transferMoney(Integer senderId, Integer receiverId, BigDecimal amount,
      String currency) throws AccountNotFoundException, NegativeBalanceException;

  /**
   * Updates an account currency
   *
   * @param id       identifier of the account to update
   * @param currency the new currency unit
   * @return the updates account
   * @throws AccountNotFoundException if no account is found with the given id
   */
  ResponseEntity<AccountDto> updateAccountCurrency(Integer id, String currency)
      throws AccountNotFoundException;
}
