package com.rafikbelas.demo.repository;

import java.util.List;

import com.rafikbelas.demo.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByAddressPostalCode(String postalCode);
    
}
