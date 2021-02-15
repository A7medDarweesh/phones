package com.jumia.exercise.service.transformer;

import com.jumia.exercise.data.Country;
import com.jumia.exercise.data.Phone;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.jumia.exercise.data.Constants.INVALID_NUMBER;
import static com.jumia.exercise.data.Constants.VALID_NUMBER;

public class PhoneTransformer
{
    private static final char CLOSING_PARENTHESES = ')';
    private static final Map<Boolean, String> STATUS_STRINGS = new HashMap<>();
    private static final EnumMap<Country, Function<String, Boolean>> NUMBER_STATUS = new EnumMap<>(Country.class);

    public PhoneTransformer()
    {
        STATUS_STRINGS.put(true, VALID_NUMBER);
        STATUS_STRINGS.put(false, INVALID_NUMBER);
        NUMBER_STATUS.put(Country.ALL, number -> false);

    }

    public Phone transform(String phoneNumber)
    {
        Country country = Country.fromPhoneNumber(phoneNumber);
        boolean valid = NUMBER_STATUS.getOrDefault(country, number -> Pattern.matches(country.getPattern(), number)).apply(phoneNumber);
        return new Phone(extractPhoneNumber(phoneNumber), country.getCodeNumber(), country.toString(), valid, STATUS_STRINGS.get(valid));
    }


    private String extractPhoneNumber(String phoneNumber)
    {
        return phoneNumber.substring(phoneNumber.indexOf(CLOSING_PARENTHESES) + 1).trim();
    }

}
