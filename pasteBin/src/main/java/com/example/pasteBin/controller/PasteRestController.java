package com.example.pasteBin.controller;

import com.example.pasteBin.dto.CreatePasteRequest;
import com.example.pasteBin.dto.PasteResponse;
import com.example.pasteBin.entity.Pastes;
import com.example.pasteBin.service.PasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping("/api/pastes")
public class PasteRestController {

    private final PasteService pasteService;

    public PasteRestController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping
    public ResponseEntity<PasteResponse> createPaste(
            @RequestBody CreatePasteRequest request) {

        Pastes paste = pasteService.createPaste(
                request.getContent(),
                request.getExpiryMinutes(),
                request.getMaxViews()
        );

        return ResponseEntity.ok(toResponse(paste));
    }

    @GetMapping("/s/{key}")
    public ResponseEntity<PasteResponse> getPasteByKey(@PathVariable String key) {

        Optional<Pastes> paste = pasteService.getPasteByKey(key);
        return paste.map(p -> ResponseEntity.ok(toResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private PasteResponse toResponse(Pastes paste) {
        return PasteResponse.builder()
                .id(paste.getId())
                .key(paste.getKey())
                .content(paste.getContent())
                .views(paste.getViews())
                .createdAt(paste.getCreatedAt())
                .expiresAt(paste.getExpiresAt())
                .build();
    }
}
