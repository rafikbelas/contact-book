package com.rafikbelas.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.rafikbelas.demo.dto.AddressCreationDTO;
import com.rafikbelas.demo.dto.AddressDTO;
import com.rafikbelas.demo.dto.ContactCreationDTO;
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
    public void registerContact(@RequestBody @Valid ContactCreationDTO contactDTO) {
        contactService.create(mapToContact(contactDTO));
    }

// TODO: replace mappers with MapStruct.

    private Contact mapToContact(ContactCreationDTO contactDTO) {
        return Contact.builder()
                .firstName(contactDTO.getFirstName())
                .lastName(contactDTO.getLastName())
                .dateOfBirth(contactDTO.getDateOfBirth())
                .address(mapToAddress(contactDTO.getAddress())).build();
    }

    private Address mapToAddress(AddressCreationDTO address) {
        return Address.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }

    private ContactsDTO mapToContactsDTO(List<Contact> contacts) {
        List<ContactDTO> contactDTOs = mapToContactDTOs(contacts);
        return new ContactsDTO(contactDTOs);
    }

    private List<ContactDTO> mapToContactDTOs(List<Contact> contacts) {
        return contacts.stream()
                .map(contact -> mapToContactDTO(contact))
                .collect(Collectors.toList());
    }

    private ContactDTO mapToContactDTO(Contact contact) {

        return ContactDTO.builder()
                .fullName(contact.getFullName())
                .dateOfBirth(contact.getFormattedDateOfBirth())
                .address(mapToAddressDTO(contact.getAddress()))
                .build();
    }

    private AddressDTO mapToAddressDTO(Address address) {
        return AddressDTO.builder()
                .addressLine(address.getAddressLine())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }
    
}
