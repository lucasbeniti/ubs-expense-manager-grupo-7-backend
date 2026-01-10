package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alert_id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "severity", nullable = false, precision = 19, scale = 6)
    private String severity;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private Long type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_expense_id")
    private Expense expense;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    public enum status {
        NEW,
        IN_ANALYSIS,
        SOLVED,
        IGNORED
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
