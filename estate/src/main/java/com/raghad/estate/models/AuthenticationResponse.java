package com.raghad.estate.models;

public class AuthenticationResponse {
    private final String JWT;

    public AuthenticationResponse(String JWT) {
        this.JWT = JWT;
    }

    public String getJWT() {
        return this.JWT;
    }
}

