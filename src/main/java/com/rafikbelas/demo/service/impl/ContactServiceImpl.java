package com.rafikbelas.demo.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.rafikbelas.demo.model.Address;
import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.service.ContactService;

import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Override
    public List<Contact> getAllContacts() {
        return Arrays.asList(
            new Contact("John", "Doe", LocalDate.of(1994, 02, 01), new Address("California", "90210")), 
            new Contact("Mike", "Brown", LocalDate.of(1993, 03, 06), new Address("New York", "94213")));
    }
    
}
