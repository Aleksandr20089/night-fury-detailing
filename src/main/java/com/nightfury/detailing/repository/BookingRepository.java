package com.nightfury.detailing.repository;

import com.nightfury.detailing.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Здесь уже есть все методы: сохранить, удалить, найти все
}