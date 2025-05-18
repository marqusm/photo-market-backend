package com.marqus.photomarketbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "hashtag")
@IdClass(HashtagId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hashtag {
    @Id
    private UUID photoId;

    @Id
    private String name;
}