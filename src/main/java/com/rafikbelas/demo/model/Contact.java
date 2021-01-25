package com.rafikbelas.demo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public String getFormattedDateOfBirth() {
		return this.dateOfBirth.format(DateTimeFormatter.ofPattern("dd LLLL yyyy"));
	}
}
