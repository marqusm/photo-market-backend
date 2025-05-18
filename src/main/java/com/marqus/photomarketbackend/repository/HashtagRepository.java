package com.marqus.photomarketbackend.repository;

import com.marqus.photomarketbackend.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HashtagRepository extends JpaRepository<Hashtag, UUID> {
}
