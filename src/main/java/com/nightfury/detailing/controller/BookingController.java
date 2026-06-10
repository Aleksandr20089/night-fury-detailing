package com.nightfury.detailing.controller;

import com.nightfury.detailing.model.Booking;
import com.nightfury.detailing.repository.BookingRepository;
import com.nightfury.detailing.repository.ServiceRepository; // Твой репозиторий услуг
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class BookingController {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository; // Чтобы клиент мог выбрать услугу из списка

    public BookingController(BookingRepository bookingRepository, ServiceRepository serviceRepository) {
        this.bookingRepository = bookingRepository;
        this.serviceRepository = serviceRepository;
    }

    // 1. Форма записи для клиента
    @GetMapping("/book")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("services", serviceRepository.findAll()); // Передаем список услуг для выпадающего меню
        return "booking-form";
    }

    // 2. Сохранение записи от клиента
    @PostMapping("/book")
    public String submitBooking(@ModelAttribute("booking") Booking booking) {
        booking.setBookingTime(LocalDateTime.now()); // Пишем текущее время записи
        booking.setStatus("НОВАЯ");
        bookingRepository.save(booking);
        return "redirect:/booking-success"; // Перенаправляем на страницу «Спасибо за заявку»
    }

    // 3. Страница успешной записи
    @GetMapping("/booking-success")
    public String bookingSuccess() {
        return "booking-success";
    }

    // 4. Панель Администратора (Список всех заказов)
    @GetMapping("/admin/bookings")
    public String showAdminBookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin-bookings";
    }

    // 5. Изменение статуса заказа (Для админа)
    @PostMapping("/admin/bookings/status/{id}")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setStatus(status);
        bookingRepository.save(booking);
        return "redirect:/admin/bookings";
    }
}