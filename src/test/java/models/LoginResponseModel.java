package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class LoginResponseModel {
    String userId;
    String username;
    String password;
    @JsonProperty("created_date")
    String createdDate;
    String expires;
    String token;
    String isActive;
}