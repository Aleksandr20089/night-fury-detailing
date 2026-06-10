package com.nightfury.detailing.model; // Проверь свой путь к пакетам!

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientPhone;

    @Column(nullable = false)
    private String carModel;

    // Связываем запись с конкретной услугой из базы данных
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    private String status = "НОВАЯ"; // Сразу даем статус: НОВАЯ, В_РАБОТЕ, ЗАВЕРШЕНА

    // Конструкторы
    public Booking() {}

    // Геттеры и Сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientPhone() { return clientPhone; }
    public void setClientPhone(String clientPhone) { this.clientPhone = clientPhone; }

    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }

    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}