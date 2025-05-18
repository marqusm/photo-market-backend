package com.marqus.photomarketbackend.repository;

import com.marqus.photomarketbackend.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
}
