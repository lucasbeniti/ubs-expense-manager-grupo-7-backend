package com.empresa.projeto.gestao_ubs.Dto.User;

import com.empresa.projeto.gestao_ubs.Entity.User.UserRole;

public record RegisterDto(String login, String password, UserRole role){
}
