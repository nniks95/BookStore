package com.nikola.spring.service;

import com.nikola.spring.dto.BookImageDto;
import com.nikola.spring.entities.BookImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookImageService {

    List<BookImageDto>  getAllImages();
    BookImageDto addImage(Integer bookId, MultipartFile multipartFile);
    void deleteImage(Integer bookId);
    BookImageDto getImageByBookId(Integer bookId);
}
