package com.rafikbelas.demo.service;

import java.util.List;

import com.rafikbelas.demo.model.Contact;

public interface ContactService {

    List<Contact> getContacts(String postalCode);

    Contact create(Contact contact);
}
