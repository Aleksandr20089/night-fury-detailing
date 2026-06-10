package com.nightfury.detailing.controller;

import com.nightfury.detailing.repository.ServiceRepository; // Импортируй репозиторий
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final ServiceRepository serviceRepository;

    // Внедряем репозиторий, чтобы достать услуги
    public MainController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Передаем список услуг в шаблон
        model.addAttribute("services", serviceRepository.findAll());
        return "index";
    }
}