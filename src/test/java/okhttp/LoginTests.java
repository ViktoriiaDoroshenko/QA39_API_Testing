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

public class LoginTests implements Helper {

    String endpoint = "/v1/user/login/usernamepassword";
    @Test
    public void loginPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("kira20@gmail.com")
                .password("2008Kira!")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO),JSON);

        Request request = new Request.Builder()
                .url(baseURL+endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){
            String responseJson = response.body().string();
            AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
            System.out.println("Response code is ---> " + response.code());
            System.out.println(responseDTO.getToken());
            Assert.assertTrue(response.isSuccessful());
        }else {
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println("Response code is ---> " + response.code());
            System.out.println(errorDTO.getStatus()+" ==== "
                    + errorDTO.getMessage()+" ==== " + errorDTO.getError());
        Assert.assertFalse(response.isSuccessful());
        }

    }
}

