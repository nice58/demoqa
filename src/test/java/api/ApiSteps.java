package api;

import io.qameta.allure.Step;
import models.CredentialsModel;
import models.LoginResponseModel;
import models.DeleteBookModel;
import models.AddBooksListModel;

import static io.restassured.RestAssured.given;
import static specs.BooksSpec.*;
import static specs.LoginSpec.*;

public class ApiSteps {

    public LoginResponseModel login(CredentialsModel credentials) {
        return given(loginRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(loginResponseSpec200)
                .extract().as(LoginResponseModel.class);
    }

    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(booksRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(booksResponseSpec204);
    }

    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList) {
        given(booksRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(booksResponseSpec201);
    }

    public void deleteBook(LoginResponseModel loginResponse, DeleteBookModel deleteBook) {
        given(booksRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(booksResponseSpec204);
    }
}
