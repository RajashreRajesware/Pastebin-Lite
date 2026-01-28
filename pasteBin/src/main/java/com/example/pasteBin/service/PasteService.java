package com.example.pasteBin.service;

import com.example.pasteBin.entity.Pastes;

import java.util.Optional;

public interface PasteService {


    Pastes createPaste(String content, Integer expiryMinutes, Integer maxViews);

    Optional<Pastes> getPaste(Long id);

    Optional<Pastes> getPasteByKey(String key);

}
