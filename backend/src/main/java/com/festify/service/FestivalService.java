package com.festify.service;

import com.festify.model.Festival;
import com.festify.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FestivalService {

    @Autowired
    private FestivalRepository festivalRepository;

    public List<Festival> findAll() {
        return festivalRepository.findAll();
    }

    public Optional<Festival> findById(Long id) {
        return festivalRepository.findById(id);
    }

    public Festival save(Festival festival) {
        return festivalRepository.save(festival);
    }
}