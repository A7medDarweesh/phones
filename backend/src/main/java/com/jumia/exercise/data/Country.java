package com.jumia.exercise.data;

import lombok.NonNull;

import java.util.Arrays;

import static com.jumia.exercise.data.Constants.*;

public enum Country
{
    ALL(DEFAULT_CODE, DEFAULT_PATTERN, DEFAULT_CODE_NUMBER), CAMEROON(CAMEROON_CODE, CAMEROON_PATTERN, CAMEROON_CODE_NUMBER), ETHIOPIA(ETHIOPIA_CODE, ETHIOPIA_PATTERN, ETHIOPIA_CODE_NUMBER), MOROCCO(MOROCCO_CODE, MOROCCO_PATTERN, MOROCCO_CODE_NUMBER), MOZAMBIQUE(MOZAMBIQUE_CODE, MOZAMBIQUE_PATTERN, MOZAMBIQUE_CODE_NUMBER), UGANDA(UGANDA_CODE, UGANDA_PATTERN, UGANDA_CODE_NUMBER);
    private final String code;
    private final String pattern;
    private final String codeNumber;

    Country(String code, String pattern, String codeNumber)
    {
        this.code = code;
        this.pattern = pattern;
        this.codeNumber = codeNumber;
    }

    public static Country fromPhoneNumber(@NonNull String phoneNumber)
    {
        return Arrays.stream(values()).filter(country -> phoneNumber.startsWith(country.code)).findFirst().orElseGet(() -> ALL);
    }
    public static Country fromString(@NonNull String countryString)
    {
        return Arrays.stream(values()).filter(country -> country.toString().equalsIgnoreCase(countryString)).findFirst().orElseGet(() -> ALL);
    }

    public String getCode()
    {
        return code;
    }

    public String getCodeNumber()
    {
        return codeNumber;
    }

    public String getPattern()
    {
        return pattern;
    }
}
