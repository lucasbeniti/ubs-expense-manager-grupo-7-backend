package com.empresa.projeto.gestao_ubs.Entity;

import com.empresa.projeto.gestao_ubs.Enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Entity(name="users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private UserRole role;


    @OneToOne(mappedBy = "user") // lado inverso
    private Employee employee;


    public User(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case UserRole.ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_FINANCE")
            );
            case UserRole.MANAGER -> List.of(new SimpleGrantedAuthority("ROLE_MANAGER"));
            case UserRole.FINANCE -> List.of(new SimpleGrantedAuthority("ROLE_FINANCE"));
            default -> List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        };
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
