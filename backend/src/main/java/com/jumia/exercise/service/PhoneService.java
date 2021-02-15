package com.jumia.exercise.service;

import com.jumia.exercise.data.Phone;
import com.jumia.exercise.service.db.PhoneDB;
import com.jumia.exercise.service.transformer.PhoneTransformer;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PhoneService
{
    PhoneDB db;
    private PhoneTransformer transformer;

    public PhoneService(PhoneDB db)
    {
        this.db = db;
        transformer = new PhoneTransformer();
    }

    public List<Phone> filteredPhones(@NonNull List<Function<List<Phone>, List<Phone>>> filters)
    {
        List<Phone> phones = db.allNumbers().stream().map(number -> transformer.transform(number)).collect(Collectors.toList());

        return filters.stream().reduce(Function.identity(), Function::andThen).apply(phones);
    }
}
