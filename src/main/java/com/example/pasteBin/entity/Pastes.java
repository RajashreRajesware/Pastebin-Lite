package com.example.pasteBin.entity;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pastes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pastes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Integer views;

    private Integer maxViews;

    @Column(nullable = false, unique = true)
    private String key;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.views = 0;
        if (this.key == null) {
            this.key = UUID.randomUUID().toString().substring(0, 8);
        }
    }
}
