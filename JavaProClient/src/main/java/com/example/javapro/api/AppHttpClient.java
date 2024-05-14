package com.example.javapro.api;

import com.example.javapro.model.response.QuizResponse;
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
    private static final String BASE_URL = "http://localhost:3000/quizzes/";

    public static List<QuizResponse> getQuizzes() throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        List<QuizResponse> quizResponses = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            quizResponses = new Gson().fromJson(response.body(), new TypeToken<List<QuizResponse>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return quizResponses;
    }

    public static QuizResponse getQuiz(String id) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + id))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        QuizResponse quizResponse = new QuizResponse();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            quizResponse = new Gson().fromJson(response.body(), QuizResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return quizResponse;
    }
}
