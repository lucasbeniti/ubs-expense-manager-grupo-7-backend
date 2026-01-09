package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "daily_limit", nullable = false, precision = 19, scale = 6)
    private BigDecimal daily_limit;

    @Column(name = "monthly_limit", nullable = false, precision = 19, scale = 6)
    private BigDecimal monthly_limit;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
