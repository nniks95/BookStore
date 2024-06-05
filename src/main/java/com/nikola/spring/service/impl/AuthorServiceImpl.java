package com.nikola.spring.service.impl;

import com.nikola.spring.converter.TempConverter;
import com.nikola.spring.dto.AuthorDto;
import com.nikola.spring.entities.AuthorEntity;
import com.nikola.spring.repositories.AuthorRepository;
import com.nikola.spring.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired private TempConverter tempConverter;
    @Autowired private AuthorRepository authorRepository;

    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {
        AuthorEntity author = tempConverter.dtoToEntity(authorDto);
        AuthorEntity storedAuthor = authorRepository.save(author);
        return tempConverter.entityToDto(storedAuthor);
    }
}
