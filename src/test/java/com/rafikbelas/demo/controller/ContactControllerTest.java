package com.rafikbelas.demo.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rafikbelas.demo.model.Address;
import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.service.ContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private ContactService contactService;

    List<Contact> contacts = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {
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
    public void givenContacts_whenGetContacts_thenReturnJsonObjectOfContacts() throws Exception {
        doReturn(contacts).when(contactService).getContacts(null);
        mock.perform(get("/contacts")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.contacts").isArray())
        .andExpect(jsonPath("$.contacts").isNotEmpty())
        .andExpect(jsonPath("$.contacts[*].fullName").isNotEmpty())
        .andExpect(jsonPath("$.contacts[*].dateOfBirth").isNotEmpty())
        .andExpect(jsonPath("$.contacts[*].address.addressLine").isNotEmpty())
        .andExpect(jsonPath("$.contacts[*].address.city").isNotEmpty())
        .andExpect(jsonPath("$.contacts[*].address.postalCode").isNotEmpty());
    }
    
    @Test
    public void givenNoContact_whenGetContactsByPostalCode_thenReturnJsonObjectWithEmptyArray() throws Exception {
        doReturn(new ArrayList<>()).when(contactService).getContacts(null);
        mock.perform(get("/contacts").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.contacts").isArray())
            .andExpect(jsonPath("$.contacts").isEmpty());
    }

    @Test
    public void givenContacts_whenGetContactsByPostalCode_thenReturnOnlyJsonObjectOfContactsWithThisPostalCode() throws Exception {
        String postalCode = "75000";
        List<Contact> filteredContacts = contacts.stream().filter(contact -> contact.getAddress().getPostalCode().equals("75000")).collect(Collectors.toList());
        doReturn(filteredContacts).when(contactService).getContacts(postalCode);
        mock.perform(get("/contacts?postalCode={postalCode}", postalCode)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.contacts[*].address.postalCode").value(postalCode));
    }

}
