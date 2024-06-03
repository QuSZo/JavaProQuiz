package com.example.javapro.api;

import com.example.javapro.model.request.createQuiz.CreateUpdateQuizRequest;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class AppHttpClient {
    private static final String BASE_URL = "http://localhost:8080/api/v1/quiz";

    public static List<GetQuizResponse> getQuizzes() throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        List<GetQuizResponse> getQuizResponse = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            getQuizResponse = new Gson().fromJson(response.body(), new TypeToken<List<GetQuizResponse>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getQuizResponse;
    }

    public static GetDetailsQuizResponse getQuiz(String id) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        GetDetailsQuizResponse getDetailsQuizResponse = new GetDetailsQuizResponse();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            getDetailsQuizResponse = new Gson().fromJson(response.body(), GetDetailsQuizResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getDetailsQuizResponse;
    }

    public static void createQuiz(CreateUpdateQuizRequest createUpdateQuizRequest) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(createUpdateQuizRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static int saveUserQuiz(UserQuizRequest userQuizRequest) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(userQuizRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/saveUserQuiz"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        return Integer.parseInt(postResponse.body());
    }

    public static void updateQuiz(CreateUpdateQuizRequest createUpdateQuizRequest) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(createUpdateQuizRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .method("PUT", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
