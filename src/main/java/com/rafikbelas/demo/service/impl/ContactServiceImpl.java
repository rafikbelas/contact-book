package com.rafikbelas.demo.service.impl;

import java.util.Arrays;
import java.util.List;

import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.service.ContactService;

import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Override
    public List<Contact> getAllContacts() {
        return Arrays.asList(
            new Contact("John", "Doe"), 
            new Contact("James", "Brown"));
    }
    
}
