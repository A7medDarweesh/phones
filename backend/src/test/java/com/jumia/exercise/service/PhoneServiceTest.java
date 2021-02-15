package com.jumia.exercise.service;

import com.jumia.exercise.data.Country;
import com.jumia.exercise.data.Phone;
import com.jumia.exercise.service.db.PhoneDB;
import com.jumia.exercise.service.filter.CountryFilters;
import com.jumia.exercise.service.filter.StatusFilters;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class PhoneServiceTest
{
    public static final String COUNTRY_PARAMETER = "morocco";
    private static final String STATUS_PARAMETER = "valid";
    static PhoneDB db;
    static List<String>numbers;
    @BeforeAll
    public static void init(){
        db=mock(PhoneDB.class);
        numbers= Arrays.asList("(212) 6617344445", "(212) 691933626", "(212) 633963130", "(212) 654642448", "(258) 847651504", "(258) 846565883");
        when(db.allNumbers()).thenReturn(numbers);
    }

    @Test
    void when_noFilterGiven_expect_allItems()
    {
       PhoneService service=new PhoneService(db);
        List<Phone> phoneLis = service.filteredPhones(Collections.emptyList());
        assertEquals(phoneLis.size(),numbers.size());

    }
    @Test
    void when_countryFilterGiven_expect_onlyGivenCountryItems()
    {
       PhoneService service=new PhoneService(db);
        CountryFilters filters=new CountryFilters();

        List<Phone> phoneLis = service.filteredPhones(Arrays.asList(filters.forCountryParameter(COUNTRY_PARAMETER)));
        assertTrue(phoneLis.stream().allMatch(phone -> phone.getCountry().equals(Country.fromString(COUNTRY_PARAMETER).toString())));

    }
    @Test
    void when_statusFilterValidGiven_expect_onlyValidItems()
    {
       PhoneService service=new PhoneService(db);
        StatusFilters filters=new StatusFilters();

        List<Phone> phoneLis = service.filteredPhones(Arrays.asList(filters.forStatusParameter(STATUS_PARAMETER)));
        assertTrue(phoneLis.stream().allMatch(Phone::isValid));

    }
    @Test
    void when_statusFilterValidAndCountryFilterGiven_expect_onlyValidAndGivenCountryItems()
    {
       PhoneService service=new PhoneService(db);
        StatusFilters statusFilter=new StatusFilters();
        CountryFilters countryFilter=new CountryFilters();
        List<Phone> phoneLis = service.filteredPhones(Arrays.asList(statusFilter.forStatusParameter(STATUS_PARAMETER),countryFilter.forCountryParameter(COUNTRY_PARAMETER)));
        assertTrue(phoneLis.stream().allMatch(Phone::isValid));
        assertTrue(phoneLis.stream().allMatch(phone -> phone.getCountry().equals(Country.fromString(COUNTRY_PARAMETER).toString())));

    }

}