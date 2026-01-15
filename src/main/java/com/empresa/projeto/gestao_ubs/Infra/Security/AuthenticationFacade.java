package com.empresa.projeto.gestao_ubs.Infra.Security;

import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Entity.User;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final EmployeeRepository employeeRepository;

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuario nao autenticado");
        }

        String username = authentication.getName();
        if (username == null || username.isEmpty()) {
            throw new RuntimeException("Usuário autenticado sem login válido");
        }

        return (User) authentication.getPrincipal();
    }

    public Employee getAuthenticatedEmployee() {
        User user = getAuthenticatedUser();
        return employeeRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Employee não encontrado para o usuário autenticado"));
    }

    public Long getAuthenticatedEmployeeId() {
        return getAuthenticatedEmployee().getId();
    }
}
