package com.nikola.spring.service.impl;

import com.nikola.spring.converter.TempConverter;
import com.nikola.spring.dto.GenreDto;
import com.nikola.spring.entities.GenreEntity;
import com.nikola.spring.repositories.GenreRepository;
import com.nikola.spring.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired private TempConverter tempConverter;
    @Autowired private GenreRepository genreRepository;

    @Override
    public GenreDto addGenre(GenreDto genreDto) {
        GenreEntity genre = tempConverter.dtoToEntity(genreDto);
        GenreEntity storedGenre = genreRepository.save(genre);
        return tempConverter.entityToDto(storedGenre);
    }

    @Override
    public void deleteGenre(Integer genreId) {
        genreRepository.deleteById(genreId);
        genreRepository.flush();
    }
}
