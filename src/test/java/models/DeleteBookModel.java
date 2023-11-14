package models;

import lombok.Data;

@Data
public class DeleteBookModel {
    IsbnModel isbn;
    String userId;
}