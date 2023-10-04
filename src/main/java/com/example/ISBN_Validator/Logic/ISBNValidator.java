package com.example.ISBN_Validator.Logic;

import org.springframework.stereotype.Component;

@Component
public class ISBNValidator {

    public boolean isValidISBN10(String isbn) {
        isbn = isbn.replaceAll("-", "").trim();

        if (isbn.length() != 10) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char c = isbn.charAt(i);
            if (i == 9 && Character.toLowerCase(c) == 'x') {
                sum += 10;
            } else if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                sum += digit * (10 - i);
            } else {
                return false;
            }
        }

        return (sum % 11 == 0);
    }

    public boolean isValidISBN13(String isbn) {
        isbn = isbn.replaceAll("-", "").trim();

        if (isbn.length() != 13) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 13; i++) {
            char c = isbn.charAt(i);
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                if (i % 2 == 0) {
                    sum += digit;
                } else {
                    sum += digit * 3;
                }
            } else {
                return false;
            }
        }

        return (sum % 10 == 0);
    }

}
