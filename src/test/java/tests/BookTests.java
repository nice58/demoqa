package tests;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.UserProfilePage;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static tests.TestData.*;

import models.LoginResponseModel;

public class BookTests extends TestBase {

    IsbnModel isbnModel = new IsbnModel();
    AddBooksListModel booksList = new AddBooksListModel();
    DeleteBookModel deleteBook = new DeleteBookModel();

    @DisplayName("Удаление книги в профиле")
    @Test
    void deleteBookFromUserProfileTest() {
        LoginResponseModel loginResponse = step("Авторизация пользователя", () ->
                apiSteps.login(credentials));

        step("Удаление всех книг в профиле пользователя", () ->
                apiSteps.deleteAllBooks(loginResponse));

        step("Подготовка тестовых данных", () -> {
        isbnModel.setIsbn(bookIsbn);
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);
        });

        step("Добавление одной книги", () ->
            apiSteps.addBook(loginResponse, booksList));

        step("Удаление добавленной книги в профиле", () -> {
        deleteBook.setIsbn(isbnModel);
        deleteBook.setUserId(loginResponse.getUserId());
        apiSteps.deleteBook(loginResponse, deleteBook);
        });

        step("Открытие профиля пользователя с авторизационными куками", () ->
        UserProfilePage.openProfileWithCookies(loginResponse));

        step("Проверка отсутствия добавленной книги", () ->
        UserProfilePage.checkDeletedBookNotPresent(bookId));
    }
}
