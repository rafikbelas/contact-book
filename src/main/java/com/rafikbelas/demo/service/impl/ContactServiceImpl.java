package com.rafikbelas.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rafikbelas.demo.model.Address;
import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.service.ContactService;

import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    List<Contact> contacts = new ArrayList<Contact>(Arrays.asList(
        new Contact("John", "Doe", LocalDate.of(1994, 02, 01), new Address("12 Walk of Fame", "Hollywood", "California", "90210")),
        new Contact("Mike", "Brown", LocalDate.of(1993, 03, 06), new Address("79 Bed Stuy Borrough", "Queens", "New York", "94213"))));

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public void create(Contact contact) {
        contacts.add(contact);
    }
    
}
