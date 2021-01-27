package com.lucifer.model;

import lombok.Data;

@Data
public class User {
    String id;
    String first;
    String last;
    String email;
    String password;
}
