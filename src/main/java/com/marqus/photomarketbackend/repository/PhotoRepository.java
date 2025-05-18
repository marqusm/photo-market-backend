package com.marqus.photomarketbackend.repository;

import com.marqus.photomarketbackend.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}
