package com.jumia.exercise.service.transformer;

import com.jumia.exercise.data.Country;
import com.jumia.exercise.data.Phone;

import java.util.regex.Pattern;

import static com.jumia.exercise.data.Constants.EMPTY_STRING;

public class PhoneTransformer
{
    private static final char CLOSING_PARENTHESES = ')';
    private static final char OPENING_PARENTHESES = '(';

    public Phone transform(String phoneNumber){
        Country country=Country.fromPhoneNumber(phoneNumber);
        return new Phone(extractPhoneNumber(phoneNumber),country.getCodeNumber(),country.toString(), Pattern.matches(country.getPattern(),phoneNumber));
    }

    private String extractPhoneNumber(String phoneNumber)
    {
        return phoneNumber.substring(phoneNumber.indexOf(CLOSING_PARENTHESES)+1);
    }

}
