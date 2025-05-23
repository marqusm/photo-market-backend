package com.marqus.photomarketbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "file")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;

    @Column(name="size")
    private Long size;

//    @Lob
//    @Column(name="content")
//    private byte[] content;
}