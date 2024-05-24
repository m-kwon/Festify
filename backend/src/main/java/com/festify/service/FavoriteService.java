package com.festify.service;

import com.festify.model.Favorite;
import com.festify.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Favorite> findByUserId(String userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }
}