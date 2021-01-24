package com.rafikbelas.demo.controllers;

import java.util.List;

import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.service.ContactService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("contacts")
@AllArgsConstructor
public class ContactController {
    
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts(@RequestParam(required = false) String postalCode) {
        return contactService.getAllContacts(postalCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerContact(@RequestBody Contact contact) {
        contactService.create(contact);
    }
}
