package com.rafikbelas.demo.controllers;

import com.rafikbelas.demo.service.ContactService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("contacts")
@AllArgsConstructor
public class ContactController {
    
    private ContactService contactService;

    @GetMapping
    public String getAllContacts(String type) {
        return contactService.getAllContacts();
    }
}
