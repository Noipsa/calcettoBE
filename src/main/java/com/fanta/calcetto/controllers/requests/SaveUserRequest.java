package com.fanta.calcetto.controllers.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveUserRequest {
    private String email;
    private String password;
    private String nome_squadra;
}
