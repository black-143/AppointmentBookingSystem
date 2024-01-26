package com.pravallika.assessment.service;

import com.pravallika.assessment.model.Appointment;
import com.pravallika.assessment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment){

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointments(LocalDate date){

        return appointmentRepository.findByAppointmentdate(date);
    }

}
