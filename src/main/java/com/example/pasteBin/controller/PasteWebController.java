package com.example.pasteBin.controller;



import com.example.pasteBin.entity.Pastes;
import com.example.pasteBin.service.PasteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PasteWebController {

    private final PasteService pasteService;

    public PasteWebController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    public String createPaste(
            @RequestParam String content,
            @RequestParam(required = false) Integer expiry,
            @RequestParam(required = false) Integer maxViews,
            Model model) {

        Pastes paste = pasteService.createPaste(content, expiry, maxViews);
        model.addAttribute("pasteCreated", paste);
        return "index";
    }

    @PostMapping("/get")
    public String getPaste(
            @RequestParam String key,
            Model model) {

        Optional<Pastes> paste = pasteService.getPasteByKey(key);
        if (paste.isEmpty()) {
            model.addAttribute("error", "Paste not found or expired");
        } else {
            model.addAttribute("pasteResult", paste.get());
        }
        return "index";
    }
}
