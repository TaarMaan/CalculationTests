package com.artamonov.tests.bek.service;

import com.artamonov.tests.bek.model.Numbers;
import com.artamonov.tests.bek.model.entity.Calculations;
import com.artamonov.tests.bek.model.enums.OperationsEnum;
import com.artamonov.tests.bek.repository.CalculationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CalculationsService {
    CalculationsRepository calculationsRepository;
    @Autowired
    public CalculationsService(CalculationsRepository repository) {
        this.calculationsRepository = repository;
    }
    public List<Calculations> getAll() {
        return calculationsRepository.findAll();
    }

    public Optional<Calculations> getById(long id) {
        return calculationsRepository.findById(id);
    }

    public void save(Calculations calculations) {
        System.out.println("save record");
        System.out.println(calculations);
        calculationsRepository.save(calculations);
    }

    public void delete(long id) {
        calculationsRepository.deleteById(id);
    }

    public void createRecordToDatabase(Numbers numbers, OperationsEnum op) {
        System.out.println("Create record to database");
        Calculations calculations = new Calculations();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        calculations.setNumberOne(String.valueOf(numbers.getNum1()));
        calculations.setNumberTwo(String.valueOf(numbers.getNum2()));
        calculations.setNumberSystemOne(numbers.getSystem());
        calculations.setNumberSystemTwo(numbers.getSystem());
        calculations.setDateCreated(date);
        calculations.setOperationsEnum(op);
        this.save(calculations);
    }
}