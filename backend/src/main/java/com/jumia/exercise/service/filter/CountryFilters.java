package com.jumia.exercise.service.filter;

import com.jumia.exercise.data.Country;
import com.jumia.exercise.data.Phone;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CountryFilters
{
    private final EnumMap<Country, Function<List<Phone>, List<Phone>>> initialFilters = new EnumMap<>(Country.class);

    public CountryFilters()
    {
        initialFilters.put(Country.ALL, (phones -> phones));
    }

    public Function<List<Phone>, List<Phone>> forCountryParameter(String countryParameter)
    {

        return initialFilters.getOrDefault(Country.fromString(countryParameter), new CountryExactFilter(countryParameter));
    }

    private static class CountryExactFilter implements Function<List<Phone>, List<Phone>>
    {

        private final String givenCountry;

        private CountryExactFilter(String givenCountry)
        {
            this.givenCountry = givenCountry;
        }

        @Override
        public List<Phone> apply(List<Phone> phones)
        {
            return phones.stream().filter(phone -> phone.getCountry().equalsIgnoreCase(givenCountry)).collect(Collectors.toList());
        }
    }
}

