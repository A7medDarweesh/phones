package com.jumia.exercise.data;

import java.util.Arrays;

public enum State
{
    VALID,INVALID,ANY;

    public static State fromString(String stateParameter)
    {
        return Arrays.stream(values()).filter(country -> country.toString().equalsIgnoreCase(stateParameter)).findFirst().orElseGet(() -> ANY);

    }
}
