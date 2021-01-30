package com.rafikbelas.demo.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafikbelas.demo.dto.AddressCreationDTO;
import com.rafikbelas.demo.dto.ContactCreationDTO;
import com.rafikbelas.demo.model.Address;
import com.rafikbelas.demo.model.Contact;
import com.rafikbelas.demo.service.ContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContactService contactService;

    List<Contact> contacts = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
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
    void givenContacts_whenGetContacts_thenReturnJsonObjectOfContacts() throws Exception {
        doReturn(contacts).when(contactService).getContacts(null);

        mock.perform(get("/contacts").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts").isArray()).andExpect(jsonPath("$.contacts").isNotEmpty())
                .andExpect(jsonPath("$.contacts[*].fullName").isNotEmpty())
                .andExpect(jsonPath("$.contacts[*].dateOfBirth").isNotEmpty())
                .andExpect(jsonPath("$.contacts[*].address.addressLine").isNotEmpty())
                .andExpect(jsonPath("$.contacts[*].address.city").isNotEmpty())
                .andExpect(jsonPath("$.contacts[*].address.postalCode").isNotEmpty());
    }

    @Test
    void givenNoContact_whenGetContactsByPostalCode_thenReturnJsonObjectWithEmptyArray() throws Exception {
        doReturn(new ArrayList<>()).when(contactService).getContacts(null);

        mock.perform(get("/contacts").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts").isArray()).andExpect(jsonPath("$.contacts").isEmpty());
    }

    @Test
    void givenContacts_whenGetContactsByPostalCode_thenReturnOnlyJsonObjectOfContactsWithThisPostalCode()
            throws Exception {
        String postalCode = "75000";
        List<Contact> filteredContacts = contacts.stream()
                .filter(contact -> contact.getAddress().getPostalCode().equals("75000")).collect(Collectors.toList());
        doReturn(filteredContacts).when(contactService).getContacts(postalCode);

        mock.perform(get("/contacts?postalCode={postalCode}", postalCode).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.contacts[*].address.postalCode").value(postalCode));
    }

    @Test
    void whenValidInput_thenRegisterContactReturns200() throws Exception {

        AddressCreationDTO addressCreationDTO = AddressCreationDTO.builder().address1("Rue Matoub Lounes")
                .city("Nantes").postalCode("69000").build();
        ContactCreationDTO contactCreationDTO = ContactCreationDTO.builder().firstName("James").lastName("Wilson")
                .dateOfBirth(LocalDate.of(1992, 03, 05)).address(addressCreationDTO).build();

        mock.perform(post("/contacts").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactCreationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenInvalidInput_thenRegisterContactReturns400() throws Exception {
        ContactCreationDTO contactCreationDTO = ContactCreationDTO.builder().firstName("James").lastName("Wilson")
                .dateOfBirth(LocalDate.of(1992, 03, 05)).address(null).build();
        mock.perform(post("/contacts").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactCreationDTO)))
                .andExpect(status().isBadRequest());
    }

}
