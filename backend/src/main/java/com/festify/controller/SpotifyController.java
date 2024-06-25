package com.festify.controller;

import com.festify.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/artist/{name}")
    public String getArtistInfo(@PathVariable String name) {
        return spotifyService.getArtistInfo(name);
    }

    @GetMapping("/artist/{id}/top-tracks")
    public String getTopTracks(@PathVariable String id) {
        return spotifyService.getTopTracks(id);
    }

    @PostMapping("/user/{userId}/playlist")
    public String createPlaylist(@PathVariable String userId, @RequestParam String name) {
        return spotifyService.createPlaylist(userId, name);
    }

    @PostMapping("/playlist/{playlistId}/tracks")
    public String addTracksToPlaylist(@PathVariable String playlistId, @RequestBody List<String> trackUris) {
        return spotifyService.addTracksToPlaylist(playlistId, trackUris);
    }
}