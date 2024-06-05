package com.nikola.spring.service.impl;

import com.nikola.spring.converter.TempConverter;
import com.nikola.spring.dto.BookImageDto;
import com.nikola.spring.entities.BookImageEntity;
import com.nikola.spring.exceptions.FileUploadException;
import com.nikola.spring.exceptions.InstanceUndefinedException;
import com.nikola.spring.repositories.BookImageRepository;
import com.nikola.spring.service.BookImageService;
import com.nikola.spring.service.BookService;
import com.nikola.spring.utils.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class BookImageServiceImpl implements BookImageService {

    @Autowired
    private TempConverter tempConverter;
    @Autowired
    private BookImageRepository bookImageRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private FileValidator fileValidator;

    @Override
    public List<BookImageDto> getAllImages() {
        List<BookImageDto> returnValue = new ArrayList<>();
        List<BookImageEntity> allImages = bookImageRepository.findAll();
        for(BookImageEntity bookImage:allImages){
            returnValue.add(tempConverter.entityToDto(bookImage));
        }
        return returnValue;
    }

    @Override
    public BookImageDto addImage(Integer bookId, MultipartFile multipartFile) {
        bookService.getBookById(bookId);
        fileValidator.validateFile(multipartFile);
        BookImageDto image = getImageByBookId(bookId);
        if(image != null){
            deleteImage(image.getBookId());
        }
        try{
            BookImageDto bookImageDto = new BookImageDto();
            bookImageDto.setBookId(bookId);
            bookImageDto.setName(StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())));
            //koristi se da se uzme od fajla originalno ime
            bookImageDto.setContentType(multipartFile.getContentType());
            bookImageDto.setSize(multipartFile.getSize());
            bookImageDto.setData(multipartFile.getBytes());
            BookImageEntity bookImageEntity = tempConverter.dtoToEntity(bookImageDto);
            BookImageEntity storedBookImage = bookImageRepository.save(bookImageEntity);
            return tempConverter.entityToDto(storedBookImage);
        }catch (Exception e){
            throw new FileUploadException(new Error("File upload failed "+ e.getMessage()));
        }
    }

    @Override
    public void deleteImage(Integer bookId) {
        Optional<BookImageEntity>bookImageEntityOptional = bookImageRepository.findByBookId(bookId);
        if(bookImageEntityOptional.isPresent()){
            bookImageRepository.deleteById(bookImageEntityOptional.get().getId());
            bookImageRepository.flush();
        }else{
            throw new InstanceUndefinedException(new Error("Book image doesn't exist"));
        }
    }

    @Override
    public BookImageDto getImageByBookId(Integer bookId) {
        BookImageDto returnValue = null;
        Optional<BookImageEntity> bookImageEntityOptional = bookImageRepository.findByBookId(bookId);
        if(bookImageEntityOptional.isPresent()){
            returnValue = tempConverter.entityToDto(bookImageEntityOptional.get());
        }
        return returnValue;
    }
}
