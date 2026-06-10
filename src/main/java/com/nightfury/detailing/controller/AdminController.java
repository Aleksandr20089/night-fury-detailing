package com.nightfury.detailing.controller;

import com.nightfury.detailing.repository.AppointmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final AppointmentRepository repository;

    public AdminController(AppointmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/admin/appointments")
    public String showAppointments(Model model) {
        model.addAttribute("appointments", repository.findAll());
        return "admin-appointments"; // Это будет файл admin-appointments.html
    }
}