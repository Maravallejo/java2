package com.parquesoftti.tc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creditcard" ,schema = "public")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 16)
    private String cardNumber;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false, length = 4)
    private String cvv;

}
