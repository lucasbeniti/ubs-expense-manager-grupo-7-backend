package com.empresa.projeto.gestao_ubs.Dto.User;

import com.empresa.projeto.gestao_ubs.Enums.UserRole;

public record RegisterDto(String login, String password, UserRole role){
}
