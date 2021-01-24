package com.rafikbelas.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contacts")
public class ContactController {
    
    @GetMapping
    public String getAllContacts() {
        return "Contacts list";
    }
}
