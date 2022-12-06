package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(long selectedId) {
        return contactRepository.findById(selectedId);
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}