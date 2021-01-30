package com.rafikbelas.demo.controller;

import java.util.List;

import javax.validation.Valid;

import com.rafikbelas.demo.dto.ContactCreationDTO;
import com.rafikbelas.demo.dto.ContactsDTO;
import com.rafikbelas.demo.mapper.ContactMapper;
import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.service.ContactService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ContactsDTO> getContacts(@RequestParam(required = false) String postalCode) {
        List<Contact> contacts = contactService.getContacts(postalCode);
        ContactsDTO contactsDTO = ContactMapper.mapToContactsDTO(contacts);
        return ResponseEntity.ok(contactsDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerContact(@RequestBody @Valid ContactCreationDTO contactDTO) {
        contactService.create(ContactMapper.mapToContact(contactDTO));
    }
    
}