package com.marqus.photomarketbackend.controller;

import com.marqus.photomarketbackend.dto.PhotoDto;
import com.marqus.photomarketbackend.entity.Account;
import com.marqus.photomarketbackend.entity.File;
import com.marqus.photomarketbackend.entity.Photo;
import com.marqus.photomarketbackend.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("/photos")
@AllArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PhotoDto> uploadPhoto(
            @AuthenticationPrincipal Account account,
            @RequestParam("content") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("hashtag") Collection<String> hashtags
    ) {
        var photo = Photo.builder()
                .accountId(account.getId())
                .name(name)
                .hashtags(hashtags)
                .build();
        var fileEntity = File.builder()
//                .content(file.getBytes())
                .size(file.getSize())
                .build();
        var photoResponse = photoService.uploadPhoto(photo, fileEntity);
        return ResponseEntity.status(201).body(new PhotoDto(photoResponse.getId()));
    }
}
