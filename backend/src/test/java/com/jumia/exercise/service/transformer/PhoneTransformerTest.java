package com.jumia.exercise.service.transformer;

import com.jumia.exercise.data.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTransformerTest
{

    @Test
    void when_numberDoesntStartWithCountryCode_expect_invalidNumber()
    {
        String phoneNumber="6007989253";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertFalse(phone.isValid());

    }
    @Test
    void when_numberStartsWithUnknownCountryCoe_expect_invalidNumber()
    {
        String phoneNumber="(220) 698054317";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertFalse(phone.isValid());

    }
    @Test
    void when_numberStartsWithKnownCountryCodeButDontMatchPattern_expect_invalidNumber()
    {
        String phoneNumber="(220) 69805431";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertFalse(phone.isValid());

    }
    @Test
    void when_numberMatchesPattern_expect_validNumber()
    {
        String phoneNumber="(212) 698054317";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertTrue(phone.isValid());

    }
    @Test
    void when_givenNonNumericNumber_expect_invalidNumber()
    {
        String phoneNumber="asd";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertFalse(phone.isValid());

    }
    @Test
    void when_givenEmptyNumber_expect_invalidNumber()
    {
        String phoneNumber="";
        PhoneTransformer transformer=new PhoneTransformer();
        Phone phone=transformer.transform(phoneNumber);
        assertFalse(phone.isValid());

    }
    @Test()
    void when_givenNullNumber_expect_exceptionThrown()
    {
        String phoneNumber=null;
        PhoneTransformer transformer=new PhoneTransformer();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            transformer.transform(phoneNumber);
        });

    }
}