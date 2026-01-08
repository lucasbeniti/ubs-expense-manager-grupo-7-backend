package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currency_id;

    @Column(name = "code", length = 3, nullable = false, unique = true)
    private String code;

    @Column(name ="name", nullable = false)
    private String name;

    @Column(name ="active", nullable = false)
    private Boolean active;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
