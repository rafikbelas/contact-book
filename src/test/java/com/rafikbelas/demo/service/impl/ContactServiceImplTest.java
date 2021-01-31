package com.rafikbelas.demo.service.impl;

import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rafikbelas.demo.model.Address;
import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.repository.ContactRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {
    
    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;
    
    List<Contact> contacts = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        Address address1 = Address.builder().address1("Rue des joviales").city("Paris").postalCode("75000").build();
        Contact contact1 = Contact.builder().id(1L).firstName("John").lastName("Doe")
                .dateOfBirth(LocalDate.of(1995, 07, 02)).address(address1).build();
        Address address2 = Address.builder().address1("Rue Vieux Port").city("Marseille").postalCode("13000").build();
        Contact contact2 = Contact.builder().id(2L).firstName("Jane").lastName("Browns")
                .dateOfBirth(LocalDate.of(1994, 06, 01)).address(address2).build();
        contacts.add(contact1);
        contacts.add(contact2);
    }

    @Test
    void givenContacts_getContactsReturnsAllContacts() throws Exception {
        doReturn(contacts).when(contactRepository).findAll();
        
        List<Contact> actualContacts = contactService.getContacts(null);

        Assertions.assertIterableEquals(contacts, actualContacts);
    }

    @Test
    void givenNoContact_getContactsReturnsNull() throws Exception {
        doReturn(null).when(contactRepository).findAll();

        List<Contact> actualContacts = contactService.getContacts(null);

        Assertions.assertIterableEquals(null, actualContacts);
    }

    @Test
    void givenContactsAndPostalCodeIsNotNull_getContactsReturnsContactsFromPostalCode() throws Exception {
        String postalCode = "75000";
        List<Contact> filteredContacts = contacts.stream()
                .filter(contact -> contact.getAddress().getPostalCode().equals(postalCode)).collect(Collectors.toList());
        doReturn(filteredContacts).when(contactRepository).findByAddressPostalCode(postalCode);

        List<Contact> actualContacts = contactService.getContacts(postalCode);

        Assertions.assertIterableEquals(filteredContacts, actualContacts);
    }
}
