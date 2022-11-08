package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.JwtToken;

public record UserLoginDto (Long id, String name, String email, boolean isMotorista, JwtToken token) {

}
