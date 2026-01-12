package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
