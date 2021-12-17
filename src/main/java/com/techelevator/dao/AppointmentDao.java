package com.techelevator.dao;

import com.techelevator.model.Appointment;
import com.techelevator.model.Office;

import java.util.List;

public interface AppointmentDao {

    // Below method just for testing purposes
    List<Appointment> findAll();

    List<Appointment> listApptsByPatientId(int PatientId);

    List<Appointment> listApptsByUserId(int userId);

    List<Appointment> listApptsByDoctorUserId(int id);

    Appointment getAppt(int apptId);


//    void newAppt(Appointment newAppointment, int id);

    Appointment newAppt(Appointment newAppointment, int id);

}
