package com.rafikbelas.demo.service.impl;

import com.rafikbelas.demo.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Override
    public String getAllContacts() {
        return "Contacts";
    }
    
}
