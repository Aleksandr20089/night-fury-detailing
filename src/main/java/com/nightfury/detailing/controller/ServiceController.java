package com.nightfury.detailing.controller;

import com.nightfury.detailing.model.Service;
import com.nightfury.detailing.repository.ServiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/services")
    public String listServices(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "services";
    }

    // Показывает пустую страницу добавления
    @GetMapping("/add-service")
    public String showAddForm(Model model) {
        model.addAttribute("service", new Service());
        return "add-service";
    }

    // Принимает данные из формы и сохраняет в БД
    @PostMapping("/add-service")
    public String addService(@ModelAttribute Service service) {
        serviceRepository.save(service);
        return "redirect:/services";
    }
    // Метод для удаления услуги
    @GetMapping("/delete-service/{id}")
    public String deleteService(@PathVariable("id") Long id) {
        serviceRepository.deleteById(id);
        return "redirect:/services";
    }
    // 1. Открывает страницу редактирования с уже заполненными данными
    @GetMapping("/edit-service/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("service", serviceRepository.findById(id).orElseThrow());
        return "add-service"; // Мы используем ту же форму, что и для добавления
    }
}