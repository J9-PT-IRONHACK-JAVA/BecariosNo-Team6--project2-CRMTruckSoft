package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findAllByAccount_Id(Long id);
    Optional<Contact> findByNameIgnoreCase(String selectedId);

}
