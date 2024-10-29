package com.bernacki.abbank.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "Name should contain at least 2 letters")
    private String name;

    @Size(min=2, message = "Surname should contain at least 2 letters")
    private String surname;

    @CreationTimestamp
    private Date dateCreated;
    private String email;
    private String phoneNumber;

    @Past
    private Date birthDate;

}
