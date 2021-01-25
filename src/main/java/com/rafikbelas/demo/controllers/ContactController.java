package com.rafikbelas.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.rafikbelas.demo.dto.AddressDTO;
import com.rafikbelas.demo.dto.ContactDTO;
import com.rafikbelas.demo.dto.ContactsDTO;
import com.rafikbelas.demo.model.Address;
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
        ContactsDTO contactsDTO = mapToContactsDTO(contacts);
        return ResponseEntity.ok(contactsDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerContact(@RequestBody Contact contact) {
        contactService.create(contact);
    }

    private ContactsDTO mapToContactsDTO(List<Contact> contacts) {
        List<ContactDTO> contactDTOs = mapToContactDTOs(contacts);
        return new ContactsDTO(contactDTOs);
    }

    private List<ContactDTO> mapToContactDTOs(List<Contact> contacts) {
        return contacts.stream()
                .map(contact -> ContactDTO.builder().firstName(contact.getFirstName()).lastName(contact.getLastName())
                        .dateOfBirth(contact.getDateOfBirth().getTime().toString())
                        .address(mapToAddressDTO(contact.getAddress())).build())
                .collect(Collectors.toList());
    }

    private AddressDTO mapToAddressDTO(Address address) {
        return AddressDTO.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }
    
}
