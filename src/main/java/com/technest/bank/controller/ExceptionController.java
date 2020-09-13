package com.technest.bank.controller;

import com.technest.bank.exception.AccountNotFoundException;
import com.technest.bank.exception.NegativeBalanceException;
import com.technest.bank.exception.TreasuryModifiedException;
import javax.money.UnknownCurrencyException;
import javax.money.convert.CurrencyConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.technest.bank")
public class ExceptionController {

  @ResponseBody
  @ExceptionHandler(AccountNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Exception getNotFoundException(Exception ex) {
    return ex;
  }

  @ResponseBody
  @ExceptionHandler(TreasuryModifiedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Exception getTreasuryModifiedException(Exception ex) {
    return ex;
  }

  @ResponseBody
  @ExceptionHandler(UnknownCurrencyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Exception getUnknownCurrencyException(Exception ex) {
    return ex;
  }

  @ResponseBody
  @ExceptionHandler(CurrencyConversionException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Exception getCurrencyConversionException(Exception ex) {
    return ex;
  }

  @ResponseBody
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Exception getIllegalArgumentException(Exception ex) {
    return ex;
  }

  @ResponseBody
  @ExceptionHandler(NegativeBalanceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Exception getNegativeBalaceException(Exception ex) {
    return ex;
  }
}
