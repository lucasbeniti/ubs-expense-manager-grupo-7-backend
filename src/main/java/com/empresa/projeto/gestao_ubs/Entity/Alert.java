package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long alertId;

    @Column(name = "alert_uuid", nullable = false, unique = true, updatable = false)
    private UUID alertUuid;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "severity", length = 20, nullable = false)
    private String severity;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "type", length = 20)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_expense_id")
    private Expense expense;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.alertUuid = UUID.randomUUID();

        if (this.status == null) {
            this.status = "new";
        }
        if (this.severity == null) {
            this.severity = "warning";
        }
    }
}

