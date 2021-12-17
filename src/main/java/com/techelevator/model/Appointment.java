package com.techelevator.model;

import java.time.LocalDateTime;

public class Appointment {
    //Instance variables
    private int apptId;
    private String apptDate;
    private String startTime;
    private String endTime;
    private String apptType;
    private int officeId;
    private int patientId;
    private int doctorId;
    private String patientNote;
    private String doctorNote;
    //additional variables
    private String doctorName;
    private String officeName;
    private String patientFirst;
    private String patientLast;
    private LocalDateTime dateAdded;
    private Double dayAge;

    //Constructors
    public Appointment() {
    }

    public Appointment(int apptId, String apptDate, String startTime, String endTime, String apptType, int officeId,
                       int patientId, int doctorId, String patientNote, String doctorNote, String doctorName, String officeName,
                       String patientFirst, String patientLast, LocalDateTime dateAdded) {
        this.apptId = apptId;
        this.apptDate = apptDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.apptType = apptType;
        this.officeId = officeId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientNote = patientNote;
        this.doctorNote = doctorNote;
        //add below
        this.doctorName = doctorName;
        this.officeName = officeName;
        this.patientFirst = patientFirst;
        this.patientLast = patientLast;
        this.dateAdded = dateAdded;
    }

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientNote() {
        return patientNote;
    }

    public void setPatientNote(String patientNote) {
        this.patientNote = patientNote;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getPatientFirst() {
        return patientFirst;
    }

    public void setPatientFirst(String patientFirst) {
        this.patientFirst = patientFirst;
    }

    public String getPatientLast() {
        return patientLast;
    }

    public void setPatientLast(String patientLast) {
        this.patientLast = patientLast;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Double getDayAge() {
        return dayAge;
    }

    public void setDayAge(Double dayAge) {
        this.dayAge = dayAge;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "apptId=" + apptId +
                ", apptDate='" + apptDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", apptType='" + apptType + '\'' +
                ", officeId=" + officeId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", patientNote='" + patientNote + '\'' +
                ", doctorNote='" + doctorNote + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", officeName='" + officeName + '\'' +
                '}';
    }
}
