package com.marqus.photomarketbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
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

    @Column
    private UUID accountId;

    @Column
    private UUID fileId;

    @Column
    private String name;

    @Column
    private String displayableStatus;

    @Column
    private String purchasableStatus;

    @ElementCollection
    @CollectionTable(name = "hashtag", joinColumns = @JoinColumn(name = "photo_id"))
    @Column(name = "name")
    private List<String> hashtags;

    private Integer likeCount;
}
