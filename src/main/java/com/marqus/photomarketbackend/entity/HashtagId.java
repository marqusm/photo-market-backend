package com.marqus.photomarketbackend.entity;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HashtagId implements Serializable {
    private UUID photoId;
    private String name;
}
