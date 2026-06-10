package com.nightfury.detailing.repository;

import com.nightfury.detailing.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}