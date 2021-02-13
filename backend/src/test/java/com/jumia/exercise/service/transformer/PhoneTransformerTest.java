package com.jumia.exercise.service.transformer;

import com.jumia.exercise.data.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTransformerTest
{

    @Test
    void should_return_invalid_when_number_doesnt_match()
    {
        String phoneNumber="(212) 6007989253";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertFalse(phone.isValid());

    }
    @Test
    void should_return_invalid_when_incorrect_code_given()
    {
        String phoneNumber="(212) 698054317";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertFalse(phone.isValid());

    }
}