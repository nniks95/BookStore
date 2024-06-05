package com.nikola.spring.service.impl;

import com.nikola.spring.converter.TempConverter;
import com.nikola.spring.dto.PublisherDto;
import com.nikola.spring.entities.PublisherEntity;
import com.nikola.spring.repositories.PublisherRepository;
import com.nikola.spring.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired private TempConverter tempConverter;
    @Autowired private PublisherRepository publisherRepository;
    @Override
    public PublisherDto addPublisher(PublisherDto publisherDto) {
        PublisherEntity publisher = tempConverter.dtoToEntity(publisherDto);
        PublisherEntity storedPublisher = publisherRepository.save(publisher);
        return tempConverter.entityToDto(storedPublisher);
    }
}
