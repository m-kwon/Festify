package com.festify.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class SpotifyService {

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    private String accessToken;

    public String getAccessToken() {
        if (accessToken == null || accessToken.isEmpty()) {
            String url = "https://accounts.spotify.com/api/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(clientId, clientSecret);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "client_credentials");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                accessToken = new JSONObject(responseBody).getString("access_token");
            } else {
                throw new RuntimeException("Failed to get access token from Spotify");
            }
        }
        return accessToken;
    }

    public String getArtistInfo(String artistName) {
        String accessToken = getAccessToken();
        String url = apiUrl + "/search?q=" + artistName + "&type=artist";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    public String getTopTracks(String artistId) {
        String accessToken = getAccessToken();
        String url = apiUrl + "/artists/" + artistId + "/top-tracks?market=US";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    public String createPlaylist(String userId, String playlistName) {
        String accessToken = getAccessToken();
        String url = apiUrl + "/users/" + userId + "/playlists";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject body = new JSONObject();
        body.put("name", playlistName);
        body.put("description", "Playlist created by Festify");
        body.put("public", false);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    public String addTracksToPlaylist(String playlistId, List<String> trackUris) {
        String accessToken = getAccessToken();
        String url = apiUrl + "/playlists/" + playlistId + "/tracks";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject body = new JSONObject();
        body.put("uris", trackUris);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}