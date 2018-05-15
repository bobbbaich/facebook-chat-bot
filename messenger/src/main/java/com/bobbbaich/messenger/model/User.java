package com.bobbbaich.messenger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String facebookId;
    private String firstName;
    private String lastName;
    private String locale;
    private Set<UserRole> userRoles;
}