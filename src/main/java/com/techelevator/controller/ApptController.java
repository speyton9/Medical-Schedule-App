package com.techelevator.controller;

import com.techelevator.model.Appointment;
import com.techelevator.model.Office;
import com.techelevator.service.ApptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ApptController {

    public ApptService apptService;

    public ApptController(ApptService apptService) {
        this.apptService = apptService;
    }

    // RETURNS ALL APPOINTMENTS
    @RequestMapping(path = "/appts", method = RequestMethod.GET)
    public List<Appointment> findAll() {
        return apptService.findAll();
    }

    // RETURNS ALL APPOINTMENTS PER PATIENT ID
    @RequestMapping(path = "/appts/patient/{patientId}", method = RequestMethod.GET)
    public List<Appointment> listApptsByPatientId(@PathVariable int patientId) {
        return apptService.listApptsByPatientId(patientId);
    }

    // RETURNS ALL APPOINTMENTS PER user ID
    @RequestMapping(path = "/appts/{userId}", method = RequestMethod.GET)
    public List<Appointment> listApptsByUserId(@PathVariable int userId) {
        return apptService.listApptsByUserId(userId);
    }

    @RequestMapping(path = "/docappts/{id}", method = RequestMethod.GET)
    public List<Appointment> listApptsByDoctorUserId(@PathVariable int id) {
        return apptService.listApptsByDoctorUserId(id);
    }

    // RETURNS ALL APPOINTMENTS PER DOCOR ID (NEED TO IMPLEMENT)

//    @RequestMapping(path = "/appts/{id}", method = RequestMethod.POST)
//    public void newAppt(@RequestBody Appointment newAppointment, @PathVariable int id) {
//        apptService.newAppt(newAppointment, id);
//    }

    // RETURNS APPOINTMENT BASED ON APPOINTMENT ID
    @RequestMapping(path = "/appts/{apptId}/details", method = RequestMethod.GET)
    public Appointment getAppt(@PathVariable int apptId) {
        return apptService.getAppt(apptId);
    }



    // INSERTS AN APPOINTMENT AND RETURNS THAT OBJECT
    @RequestMapping(path = "/appts/{id}", method = RequestMethod.POST)
    public Appointment newAppt(@RequestBody Appointment newAppointment, @PathVariable int id) {
        return apptService.newAppt(newAppointment, id);
    }

}
