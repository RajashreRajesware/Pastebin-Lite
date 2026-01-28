package com.example.pasteBin.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder

public class PasteResponse {

    private Long id;
    private String key;
    private String content;
    private Integer views;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

}


