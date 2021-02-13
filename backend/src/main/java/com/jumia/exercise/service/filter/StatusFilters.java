package com.jumia.exercise.service.filter;

import com.jumia.exercise.data.Country;
import com.jumia.exercise.data.Phone;
import com.jumia.exercise.data.State;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.jumia.exercise.data.State.INVALID;
import static com.jumia.exercise.data.State.VALID;

@Service
public class StatusFilters
{
    private final Map<State, Function<List<Phone>, List<Phone>>> initialFilters = new HashMap<>();

    public StatusFilters()
    {
        initialFilters.put(VALID,(phones ->phones.stream().filter(Phone::isValid).collect(Collectors.toList())));
        initialFilters.put(INVALID,(phones ->phones.stream().filter(Phone::isNotValid).collect(Collectors.toList())));
    }

    public Function<List<Phone>, List<Phone>> forStatusParameter(String stateParameter)
    {

        return initialFilters.getOrDefault(State.fromString(stateParameter),(phones -> phones) );
    }
}
