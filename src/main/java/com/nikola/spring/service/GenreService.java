package com.nikola.spring.service;

import com.nikola.spring.dto.GenreDto;

public interface GenreService {

    GenreDto addGenre(GenreDto genreDto);

    void deleteGenre(Integer genreId);
}
