package com.rafikbelas.demo.repository;

import java.util.List;

import com.rafikbelas.demo.model.Contact;

public interface ContactRepository {
    
    List<Contact> findAll();

    List<Contact> findByPostalCode(String postalCode);
    
    void create(Contact contact);
}
