package com.festify.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyService {

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken() {
        // Implement OAuth2 to get an access token from Spotify
        return "access_token";
    }

    public String getArtistInfo(String artistName) {
        String accessToken = getAccessToken();
        String url = apiUrl + "/search?q=" + artistName + "&type=artist";

        // Add headers for Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    // Implement methods to get top tracks and create playlists
}
