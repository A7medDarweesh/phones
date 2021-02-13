package com.jumia.exercise.service;

import com.jumia.exercise.data.Phone;
import com.jumia.exercise.service.db.PhoneDB;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class PhoneService
{
    PhoneDB db;

    public PhoneService(PhoneDB db)
    {
        this.db = db;
    }

   public List<Phone>FilteredPhones(@NonNull List<Function<List<Phone>,List<Phone>>>filters){
        List<Phone> phones = db.allNumbers();

        return filters.stream().reduce(Function.identity(), Function::andThen).apply(phones);
    }
}
