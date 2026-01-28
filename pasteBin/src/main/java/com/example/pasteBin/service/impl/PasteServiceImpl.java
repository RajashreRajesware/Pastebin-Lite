package com.example.pasteBin.service.impl;

import com.example.pasteBin.entity.Pastes;
import com.example.pasteBin.repository.PasteRepository;
import com.example.pasteBin.service.PasteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasteServiceImpl  implements PasteService {

    private final PasteRepository pasteRepository;

    public PasteServiceImpl(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    @Override

    public Pastes createPaste(String content, Integer expiryMinutes, Integer maxViews) {
        Pastes paste = Pastes.builder()
                .content(content)
                .maxViews(maxViews)
                .build();

        if (expiryMinutes != null && expiryMinutes > 0) {
            paste.setExpiresAt(LocalDateTime.now().plusMinutes(expiryMinutes));
        }

        return pasteRepository.save(paste);
    }



    @Override
    public Optional<Pastes> getPaste(Long id) {

        Optional<Pastes> optionalPaste = pasteRepository.findById(id);

        if (optionalPaste.isEmpty()) {
            return Optional.empty();
        }

        Pastes paste = optionalPaste.get();


        if (paste.getExpiresAt() != null &&
                paste.getExpiresAt().isBefore(LocalDateTime.now())) {
            pasteRepository.delete(paste);
            return Optional.empty();
        }


        if (paste.getMaxViews() != null &&
                paste.getViews() >= paste.getMaxViews()) {
            pasteRepository.delete(paste);
            return Optional.empty();
        }


        paste.setViews(paste.getViews() + 1);
        pasteRepository.save(paste);

        return Optional.of(paste);
    }
    @Override
    public Optional<Pastes> getPasteByKey(String key) {
        Optional<Pastes> optionalPaste = pasteRepository.findByKey(key);

        if (optionalPaste.isEmpty()) return Optional.empty();

        Pastes paste = optionalPaste.get();


        if (paste.getExpiresAt() != null && paste.getExpiresAt().isBefore(LocalDateTime.now())) {
            pasteRepository.delete(paste);
            return Optional.empty();
        }


        if (paste.getMaxViews() != null && paste.getViews() >= paste.getMaxViews()) {
            pasteRepository.delete(paste);
            return Optional.empty();
        }


        paste.setViews(paste.getViews() + 1);
        pasteRepository.save(paste);

        return Optional.of(paste);
    }


}
