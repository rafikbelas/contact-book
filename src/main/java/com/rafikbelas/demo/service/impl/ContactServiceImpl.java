package com.rafikbelas.demo.service.impl;

import java.util.List;

import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.repository.ContactRepository;
import com.rafikbelas.demo.service.ContactService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    @Override
    public List<Contact> getContacts(String postalCode) {
        if (postalCode == null) 
            return contactRepository.findAll();
        else
            return contactRepository.findByPostalCode(postalCode);
    }

    @Override
    public void create(Contact contact) {
        contactRepository.create(contact);
    }
    
}
