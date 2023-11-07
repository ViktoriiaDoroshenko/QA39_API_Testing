package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTests implements Helper {

    String endpoint = "/v1/user/login/usernamepassword";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void loginPositive(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("kira20@gmail.com")
                .password("2008Kira!")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());

    }


    @Test
    public void loginNegativeWrongEmail(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("kira20gmail.com")
                .password("2008Kira!")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());

    }

}
