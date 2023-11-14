package tests;

import models.CredentialsModel;

public class TestData {
    public static String username = "Natasha",
            password = "Natasha1!1",
            bookIsbn = "9781449325862",
            bookId = "see-book-Git Pocket Guide";

    public static CredentialsModel credentials = new CredentialsModel(username, password);
}
