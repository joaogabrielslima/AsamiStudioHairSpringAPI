package com.br.asamistudiohair.controller;

import com.br.asamistudiohair.model.Appointment;
import com.br.asamistudiohair.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.created(URI.create("/api/appointments/" + savedAppointment.getId())).body(savedAppointment);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment updatedAppointment) {
        if (!appointmentRepository.existsById((long) id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAppointment.setId(id);
        Appointment savedAppointment = appointmentRepository.save(updatedAppointment);
        return ResponseEntity.ok(savedAppointment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        if (!appointmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        appointmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}