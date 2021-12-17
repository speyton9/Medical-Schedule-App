package com.techelevator.service;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Appointment;
import com.techelevator.model.Office;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
public class ApptService {

    private AppointmentDao appointmentDao;

    public ApptService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;

    }

    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    public List<Appointment> listApptsByPatientId(int patientId) {
        return appointmentDao.listApptsByPatientId(patientId);
    }

    public List<Appointment> listApptsByUserId(int userId) {
        return appointmentDao.listApptsByUserId(userId);
    }

    public List<Appointment> listApptsByDoctorUserId(int id) {
        return appointmentDao.listApptsByDoctorUserId(id);
    }

    public Appointment getAppt(int apptId) {
        return appointmentDao.getAppt(apptId);
    }


//    public void newAppt(Appointment newAppointment, int id) {
//        appointmentDao.newAppt(newAppointment, id);
//    }


    public Appointment newAppt(Appointment newAppointment, int id) {
        return appointmentDao.newAppt(newAppointment, id);
    }

}