package com.marqus.photomarketbackend.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public record PhotoDto(
        UUID id,
        UUID fileId,
        UUID accountId,
        String name,
        String displayableStatus,
        String purchasableStatus,
        List<String> hashtags,
        Integer likeCount,
        MultipartFile content) {
    public PhotoDto(UUID id) {
        this(id, null, null, null, null, null, null, null, null);
    }
}
