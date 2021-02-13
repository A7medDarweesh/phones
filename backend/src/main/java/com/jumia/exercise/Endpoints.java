package com.jumia.exercise;

import com.jumia.exercise.data.Country;
import com.jumia.exercise.data.Phone;
import com.jumia.exercise.service.PhoneService;
import com.jumia.exercise.service.filter.CountryFilters;
import com.jumia.exercise.service.filter.StatusFilters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Endpoints
{
    private final CountryFilters countryFilters;
    private final StatusFilters statusFilters;
    private final PhoneService phoneService;

    public Endpoints(CountryFilters countryFilters, StatusFilters statusFilters, PhoneService phoneService)
    {
        this.countryFilters = countryFilters;
        this.statusFilters = statusFilters;
        this.phoneService = phoneService;
    }

    @CrossOrigin
    @GetMapping("/phones")
    public ResponseEntity<List<Phone>> check(@RequestParam(value = "country",defaultValue = "")String countryParameter,@RequestParam(value = "status",defaultValue = "")String statusParameter)
    {

        Function<List<Phone>, List<Phone>> countryFilter = countryFilters.forCountryParameter(countryParameter);
        Function<List<Phone>, List<Phone>> statusFilter = statusFilters.forStatusParameter(statusParameter);

        return new ResponseEntity<>(phoneService.FilteredPhones(Arrays.asList(countryFilter,statusFilter)), HttpStatus.OK);


    }
    @CrossOrigin
    @GetMapping("/countries")
    public ResponseEntity<List<String>> countries()
    {

        List<String> countries = Arrays.stream(Country.values()).map(Country::toString).collect(Collectors.toList());
        return new ResponseEntity<>(countries, HttpStatus.OK);


    }
}
