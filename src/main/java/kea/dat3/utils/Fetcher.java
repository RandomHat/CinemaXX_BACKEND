package kea.dat3.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.dat3.error.Client4xxException;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

public class Fetcher {
    private String url;
    private String api_token;
    private String json;
    private ObjectMapper mapper = new ObjectMapper();

    public Fetcher(String url){
        this.url = url;
        this.api_token = System.getenv("API_TOKEN");
    }

    public void fetch() throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .headers("Content-Type", "application/json;charset=UTF-8", "Authorization", "Bearer " + api_token)
                .build();

        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply((response) -> {
                    if (response.statusCode() != 200){
                    throw new Client4xxException(response.body(), HttpStatus.valueOf(response.statusCode()));
                    }
                    return response.body();
                }).thenAccept((data) -> json = data)
                .join();
        }

    public Map<String, Object> getFetchedMap() throws JsonProcessingException{
        return mapper.readValue(json, Map.class);
    }

    public JsonNode asJsonNode() throws JsonProcessingException{
        return mapper.readTree(json);
    }

    public String getJson(){
        return json;
    }
}
