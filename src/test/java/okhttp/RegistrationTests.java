package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements Helper {
    // token = eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoia2lyYTgwQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjk5Nzg0NzA3LCJpYXQiOjE2OTkxODQ3MDd9.Wd4ZB1mUS6OLGdK4tCwP1d6h0TYxt4i1gOGztnN1ZhY
    String endpoint = "/v1/user/registration/usernamepassword";
    @Test
    public void registrationPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("kira"+i+"@gmail.com")
                .password("2008Kira!")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO),JSON);

        Request request = new Request.Builder()
                .url(baseURL+ endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
    }
    @Test
    public void registrationNegativeWrongEmail() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("kira"+i+"gmail.com")
                .password("2008Kira!")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO),JSON);

        Request request = new Request.Builder()
                .url(baseURL+ endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println("Response code is ---> " + response.code());
        System.out.println(errorDTO.getStatus()+" ==== "
                + errorDTO.getMessage()+" ==== " + errorDTO.getError());
        Assert.assertTrue(!response.isSuccessful());
    }
}
