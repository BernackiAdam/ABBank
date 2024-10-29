package com.bernacki.abbank.account_service.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Random;

@Service
public class IBANService {

    String BANK_CODE = "33334444";

    public String generateIban(String countryCode){
        String accountNumber = BANK_CODE +
                generateRandomNumber();
        BigInteger accNumber = new BigInteger(accountNumber + countryCodeToNumber(countryCode) + "00");
        int checkSum = 98 - accNumber.mod(BigInteger.valueOf(97)).intValue();
        return countryCode + String.format("%02d", checkSum) + accountNumber;
    }

    private String generateRandomNumber(){
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for(int i=0; i<16; i++){
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    private String countryCodeToNumber(String countryCode){
        StringBuilder countryCodeNumber = new StringBuilder();
        countryCode = countryCode.toUpperCase();
        for(int i=0; i<countryCode.length(); i++){
            char ch = countryCode.charAt(i);
            int numericValue = ch-55;
            countryCodeNumber.append(numericValue);
        }
        return countryCodeNumber.toString();
    }
}
