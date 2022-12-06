package com.ironhack.team6crm.service;


import com.ironhack.team6crm.model.SalesRep;
import com.ironhack.team6crm.repository.SalesRepRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesRepService {

    private final SalesRepRepository salesRepRepository;

    public SalesRepService(SalesRepRepository salesRepRepository) {
        this.salesRepRepository = salesRepRepository;
    }


    public List<SalesRep> findAll() {
        return salesRepRepository.findAll();
    }

    public Optional<SalesRep> findById(long selectedId) {
        return salesRepRepository.findById(selectedId);
    }

    public SalesRep save(SalesRep user) {
        return salesRepRepository.save(user);
    }
}