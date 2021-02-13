package com.jumia.exercise.service.db;

import com.jumia.exercise.data.Phone;
import com.jumia.exercise.service.transformer.PhoneTransformer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneDB
{
    JdbcTemplate template;
    private PhoneTransformer transformer;

    public PhoneDB(JdbcTemplate template)
    {
        this.template = template;
        transformer=new PhoneTransformer();
    }
    public List<Phone>allNumbers(){
        return template.query("SELECT phone from customer",
                (resultSet, rowNum) -> transformer.transform(resultSet.getString("phone")));

    }
}
