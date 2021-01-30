package com.rafikbelas.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.rafikbelas.demo.dto.AddressCreationDTO;
import com.rafikbelas.demo.dto.AddressDTO;
import com.rafikbelas.demo.dto.ContactCreationDTO;
import com.rafikbelas.demo.dto.ContactDTO;
import com.rafikbelas.demo.dto.ContactsDTO;
import com.rafikbelas.demo.model.Address;
import com.rafikbelas.demo.model.Contact;

public class ContactMapper {
    private ContactMapper() {}
    // TODO: replace mappers with MapStruct.

    public static Contact mapToContact(ContactCreationDTO contactDTO) {
        return Contact.builder()
                .firstName(contactDTO.getFirstName())
                .lastName(contactDTO.getLastName())
                .dateOfBirth(contactDTO.getDateOfBirth())
                .address(mapToAddress(contactDTO.getAddress())).build();
    }

    private static Address mapToAddress(AddressCreationDTO address) {
        return Address.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static ContactsDTO mapToContactsDTO(List<Contact> contacts) {
        List<ContactDTO> contactDTOs = mapToContactDTOs(contacts);
        return new ContactsDTO(contactDTOs);
    }

    private static List<ContactDTO> mapToContactDTOs(List<Contact> contacts) {
        return contacts.stream()
                .map(ContactMapper::mapToContactDTO)
                .collect(Collectors.toList());
    }

    public static ContactDTO mapToContactDTO(Contact contact) {

        return ContactDTO.builder()
                .fullName(contact.getFullName())
                .dateOfBirth(contact.getFormattedDateOfBirth())
                .address(mapToAddressDTO(contact.getAddress()))
                .build();
    }

    private static AddressDTO mapToAddressDTO(Address address) {
        return AddressDTO.builder()
                .addressLine(address.getAddressLine())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }
}
