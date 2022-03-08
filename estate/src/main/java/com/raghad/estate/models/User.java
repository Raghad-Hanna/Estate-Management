package com.raghad.estate.models;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ID;
    private String username;
    private String password;
    private String authority = "ROLE_USER";
}
