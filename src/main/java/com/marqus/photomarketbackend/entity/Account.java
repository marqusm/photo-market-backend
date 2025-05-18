package com.marqus.photomarketbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "account")
@Data                       // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // No-arg constructor
@AllArgsConstructor         // All-args constructor
@Builder
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String username;

    @Column
    private String passwordHash;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String name;
}
