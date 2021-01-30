package com.rafikbelas.demo.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Address address = Address.builder().address1("Rue des joviales").city("Paris").postalCode("75000").build();
        Contact contact = Contact.builder().id(1L).firstName("John").lastName("Doe")
                .dateOfBirth(LocalDate.of(1994, 06, 01)).address(address).build();
        contacts.add(contact);
    }

    @Test
    public void givenContacts_whenGetContacts_thenReturnJsonObjectOfContacts() throws Exception {
        doReturn(contacts).when(contactService).getContacts(null);
        mock.perform(get("/contacts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.contacts[0].fullName").value("John Doe"))
            .andExpect(jsonPath("$.contacts[0].address.addressLine").value("Rue des joviales"))
            .andExpect(jsonPath("$.contacts[0].address.city").value("Paris"))
            .andExpect(jsonPath("$.contacts[0].address.postalCode").value("75000"));
    }

}
