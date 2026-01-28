package com.example.pasteBin.repository;

import com.example.pasteBin.entity.Pastes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasteRepository extends JpaRepository <Pastes, Long> {

    Optional<Pastes> findByKey(String key);

}
