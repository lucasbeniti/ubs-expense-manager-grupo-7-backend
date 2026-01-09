package com.empresa.projeto.gestao_ubs.Entity.User;

public enum UserRole {
    ADMIN("admin"),
    EMPLOYEE("employee"),
    MANAGER("manager"),
    FINANCE("finance");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
