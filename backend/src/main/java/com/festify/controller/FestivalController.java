package com.festify.controller;

import com.festify.model.Favorite;
import com.festify.model.Festival;
import com.festify.service.FavoriteService;
import com.festify.service.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FestivalController {

    @Autowired
    private FestivalService festivalService;

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/festivals")
    public List<Festival> getFestivals() {
        return festivalService.findAll();
    }

    @GetMapping("/festivals/{id}")
    public Optional<Festival> getFestival(@PathVariable Long id) {
        return festivalService.findById(id);
    }

    @PostMapping("/favorites")
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoriteService.save(favorite);
    }

    @GetMapping("/favorites/{userId}")
    public List<Favorite> getFavorites(@PathVariable String userId) {
        return favoriteService.findByUserId(userId);
    }

    @DeleteMapping("/favorites")
    public void removeFavorite(@RequestBody Favorite favorite) {
        favoriteService.delete(favorite);
    }
}