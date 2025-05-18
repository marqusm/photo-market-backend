package com.marqus.photomarketbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID accountId;

    private UUID fileId;

    private String name;

    @Enumerated(EnumType.STRING)
    private DisplayableStatus displayableStatus;

    @Enumerated(EnumType.STRING)
    private PurchasableStatus purchasableStatus;

    @Transient
    private Collection<String> hashtags;

    @Transient
    private Integer likeCount;
}
