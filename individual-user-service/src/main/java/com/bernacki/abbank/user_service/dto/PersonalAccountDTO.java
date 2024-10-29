package com.bernacki.abbank.user_service.dto;

import java.math.BigDecimal;

public class PersonalAccountDTO {
    private String accountNumber;
    private Long userId;
    private String accountType;
    private BigDecimal balance;
    private String currency;
    private BigDecimal debtLimit;
    private BigDecimal withdrawLimit;
}
