package com.festify.controller;

import com.festify.model.Artist;
import com.festify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return artistService.findAll();
    }

    @PostMapping("/artists")
    public Artist addArtist(@RequestBody Artist artist) {
        return artistService.save(artist);
    }
}