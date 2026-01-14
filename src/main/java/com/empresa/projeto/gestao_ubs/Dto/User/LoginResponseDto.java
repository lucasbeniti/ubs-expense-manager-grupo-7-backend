package com.empresa.projeto.gestao_ubs.Dto.User;

public record LoginResponseDto(String token, String name, String login, String role, Long employeeId) {
}
