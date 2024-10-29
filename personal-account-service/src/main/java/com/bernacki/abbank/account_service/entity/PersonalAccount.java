package com.bernacki.abbank.account_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class PersonalAccount {

    @Id
    private String accountNumber;
    private Long userId;
    private String accountType = "Personal";
    private BigDecimal balance = BigDecimal.ZERO;
    private String currency = "PLN";
    private BigDecimal debtLimit = BigDecimal.valueOf(500);
    private BigDecimal withdrawLimit = BigDecimal.valueOf(1500);

    public PersonalAccount(String accountNumber, Long userId) {
        this.accountNumber = accountNumber;
        this.userId = userId;
    }
}

