package com.marqus.photomarketbackend.service;

import com.marqus.photomarketbackend.entity.*;
import com.marqus.photomarketbackend.repository.FileRepository;
import com.marqus.photomarketbackend.repository.HashtagRepository;
import com.marqus.photomarketbackend.repository.PhotoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhotoService {

    private PhotoRepository photoRepo;
    private FileRepository fileRepo;
    private HashtagRepository hashtagRepo;

    @Transactional
    public Photo uploadPhoto(Photo photo, File file) {
        fileRepo.save(file);
        photo.setFileId(file.getId());
        photo.setDisplayableStatus(DisplayableStatus.INITIAL);
        photo.setPurchasableStatus(PurchasableStatus.INITIAL);
        photoRepo.save(photo);
        for(var hashtagVal: photo.getHashtags()) {
            var hashtag = Hashtag.builder().photoId(photo.getId()).name(hashtagVal).build();
            hashtagRepo.save(hashtag);
        }
        return photo;
    }

}
