package com.pravallika.assessment.repository;

import com.pravallika.assessment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByAppointmentdate(LocalDate appointmentdate);
}
