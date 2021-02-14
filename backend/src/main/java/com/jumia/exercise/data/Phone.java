package com.jumia.exercise.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.jumia.exercise.data.Constants.INVALID_NUMBER;
import static com.jumia.exercise.data.Constants.VALID_NUMBER;

@Data
@AllArgsConstructor
public class Phone
{
    private String number;
    private String code;
    private String country;
    @JsonIgnore
    private boolean valid;
    private String state;

}
