package com.edu.medproject.api.domain.user;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UserAuthenticatorDTO(@JsonAlias("login") String login,
                                   @JsonAlias("senha") String password) {
}
