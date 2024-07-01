package com.example.javapro.api;

import com.example.javapro.auth.UserSession;
import com.example.javapro.exceptions.HttpException;
import com.example.javapro.model.auth.JwtDto;
import com.example.javapro.model.auth.SignInDto;
import com.example.javapro.model.auth.SignUpDto;
import com.example.javapro.model.request.createQuiz.CreateUpdateQuizRequest;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;
import com.example.javapro.model.response.getLab.Lab;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;
import com.example.javapro.model.response.getQuizUserScore.GetQuizUserScoreResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AppHttpClient {
    private static final String BASE_URL = "http://localhost:8080/api/v1";
    private static final String QUIZ_URL = BASE_URL + "/quiz";
    private static final String LAB_URL = BASE_URL + "/lab";
    private static final String AUTH_URL = BASE_URL + "/auth";

    public static List<GetQuizResponse> getQuizzes() throws IOException {
        HttpRequest request;
        if(UserSession.getInstance().isAuthenticated()){
            request = HttpRequest.newBuilder()
                    .uri(URI.create(QUIZ_URL))
                    .header("Authorization", "Bearer " + UserSession.getInstance().getToken())
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
        }
        else {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(QUIZ_URL))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
        }


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
                .uri(URI.create(QUIZ_URL + "/" + id))
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
                .uri(URI.create(QUIZ_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + UserSession.getInstance().getToken())
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static int saveUserQuiz(UserQuizRequest userQuizRequest) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(userQuizRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(QUIZ_URL + "/saveUserQuiz"))
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
                .uri(URI.create(QUIZ_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + UserSession.getInstance().getToken())
                .method("PUT", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static JwtDto signIn(SignInDto signInDto) throws IOException, InterruptedException, HttpException {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(signInDto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL + "/signin"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() != 200) {
            throw new HttpException();
        }

        JwtDto jwtDto;
        jwtDto = new Gson().fromJson(httpResponse.body(), JwtDto.class);

        return jwtDto;
    }

    public static void signUp(SignUpDto signUpDto) throws IOException, InterruptedException, HttpException {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(signUpDto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL + "/signup"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() != 201) {
            throw new HttpException();
        }
    }

    public static List<GetQuizUserScoreResponse> getQuizUserScoreResponses(String quizId){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(QUIZ_URL + "/" + quizId + "/scores"))
                .header("Authorization", "Bearer " + UserSession.getInstance().getToken())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        List<GetQuizUserScoreResponse> getQuizUserScoreResponses = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            getQuizUserScoreResponses = new Gson().fromJson(response.body(), new TypeToken<List<GetQuizUserScoreResponse>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getQuizUserScoreResponses;
    }

    public static List<Lab> getLabs() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LAB_URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Lab> labs = new Gson().fromJson(response.body(), new TypeToken<List<Lab>>(){}.getType());

        return labs;
    }
}
