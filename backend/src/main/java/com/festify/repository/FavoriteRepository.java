package com.festify.repository;

import com.festify.model.Favorite;
import com.festify.model.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    List<Favorite> findByUserId(String userId);
}