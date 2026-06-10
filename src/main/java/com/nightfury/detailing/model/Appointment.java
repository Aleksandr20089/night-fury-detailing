package com.nightfury.detailing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String phone;
    private String serviceName;
    private String status = "В очереди";

    // Конструктор по умолчанию
    public Appointment() {}

    public Appointment(String clientName, String phone, String serviceName, String status) {
        this.clientName = clientName;
        this.phone = phone;
        this.serviceName = serviceName;
        this.status = status;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}