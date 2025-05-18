package com.marqus.photomarketbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private UUID sellerAccountId;

    @Column
    private UUID buyerAccountId;

    @Column
    private UUID photoId;

    @Column
    private String externalTransactionId;

    @Column
    private Long amount;

    @Column
    private String currency;

    @Column
    private String buyerName;

    @Column
    private String buyerAddress;

    @Column
    private String buyerEmail;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String status;
}
