package com.technest.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "balance", nullable = false)
  @Lob
  private Money balance;

  @Column(name = "treasury", nullable = false)
  private Boolean treasury;
}
