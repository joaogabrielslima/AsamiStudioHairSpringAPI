package com.br.asamistudiohair.repository;

import com.br.asamistudiohair.model.Appointment;
import com.br.asamistudiohair.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentByClient(User client);
    List<Appointment> findAppointmentByEmployee(User employee);

}