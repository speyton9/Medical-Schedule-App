package com.techelevator.dao;

import com.techelevator.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcAppointmentDao implements AppointmentDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAppointmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "select * from appointments";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Appointment appt  = mapRowToAppointment(results);
            appointments.add(appt);
        }

        return appointments;
    }

    @Override
    public List<Appointment> listApptsByPatientId(int PatientId) {
        List<Appointment> appts = new ArrayList<>();
        String sql = "select appt_id, appt_date, da.last_name AS doctor_name, appt_type, start_time, end_time, " +
                "o.name AS name, a.office_id, a.patient_id, a.doctor_id, doctor_note, patient_note, pa.first_name AS patient_first, date_added, " +
                "(SELECT EXTRACT( DAYS FROM current_timestamp - date_added )) AS day_age, " +
                "pa.last_name AS patient_last from appointments a\n" +
                "join patient_accounts pa ON pa.patient_id = a.patient_id\n" +
                "join doctor_accounts da ON da.doctor_id = a.doctor_id\n" +
                "join offices o ON o.office_id = a.office_id\n" +
                "join users u ON u.user_id = pa.user_id\n" +
                "where pa.patient_id= ?\n" +
                "order by appt_date;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, PatientId);
        while (results.next()) {
            Appointment appt = mapRowToAppointmentWithDayAge(results);
            appts.add(appt);
        }

        return appts;
    }

    @Override
    public List<Appointment> listApptsByUserId(int userId) {
        List<Appointment> appts = new ArrayList<>();
        String sql = "select appt_id, appt_date, da.last_name AS doctor_name, appt_type, start_time, end_time, " +
                "o.name AS name, a.office_id, a.patient_id, a.doctor_id, doctor_note, patient_note, pa.first_name AS patient_first, " +
                "date_added, " +
                "(SELECT  EXTRACT( DAYS FROM current_timestamp - date_added ) AS day_age), " +
                "pa.last_name AS patient_last from appointments a\n" +
                "join patient_accounts pa ON pa.patient_id = a.patient_id\n" +
                "join doctor_accounts da ON da.doctor_id = a.doctor_id\n" +
                "join offices o ON o.office_id = a.office_id\n" +
                "join users u ON u.user_id = pa.user_id\n" +
                "where pa.user_id= ?\n" +
                "order by appt_date DESC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Appointment appt = mapRowToAppointmentWithDayAge(results);
            appts.add(appt);
        }

        return appts;
    }

    @Override
    public List<Appointment> listApptsByDoctorUserId(int id) {
        List<Appointment> appts = new ArrayList<>();
        String sql = "select appt_id, appt_date, da.last_name AS doctor_name, appt_type, start_time, end_time, " +
                "o.name AS name, a.office_id, a.patient_id, a.doctor_id, doctor_note, patient_note, pa.first_name AS patient_first, " +
                "date_added, " +
                "(SELECT  EXTRACT( DAYS FROM current_timestamp - date_added ) AS day_age), " +
                "pa.last_name AS patient_last from appointments a\n" +
                "join patient_accounts pa ON pa.patient_id = a.patient_id\n" +
                "join doctor_accounts da ON da.doctor_id = a.doctor_id\n" +
                "join offices o ON o.office_id = a.office_id\n" +
                "join users u ON u.user_id = da.user_id\n" +
                "where u.user_id = ?\n" +
                "order by appt_date DESC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            Appointment appt = mapRowToAppointmentWithDayAge(results);
            appts.add(appt);
        }

        return appts;
    }



    @Override
    public Appointment getAppt(int apptId) {
        Appointment appt = new Appointment();
        String sql = "select appt_id, appt_date, da.last_name AS doctor_name, appt_type, start_time, end_time, pa.first_name AS patient_first, pa.last_name AS patient_last, " +
                "o.name AS name, a.office_id, a.patient_id, a.doctor_id, doctor_note, date_added, patient_note from appointments a\n" +
                "join patient_accounts pa ON pa.patient_id = a.patient_id\n" +
                "join doctor_accounts da ON da.doctor_id = a.doctor_id\n" +
                "join offices o ON o.office_id = a.office_id\n" +
                "where a.appt_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, apptId);
        while (results.next()) {
            appt = mapRowToAppointment(results);
        }

        return appt;
    }

//    @Override
//    public void newAppt(Appointment newAppointment, int userId) {
//        String sql = "INSERT INTO appointments (appt_id, appt_date, start_time, end_time, appt_type, office_id, doctor_id, patient_note, patient_id)" +
//        "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, (SELECT patient_id FROM patient_accounts pa WHERE pa.user_id = ? ));";
//
//        jdbcTemplate.update(sql, newAppointment.getApptDate(), newAppointment.getStartTime(), newAppointment.getEndTime(),
//                newAppointment.getApptType(), newAppointment.getOfficeId(), newAppointment.getDoctorId(),
//                newAppointment.getPatientNote(), userId);
//
//    }

    @Override
    public Appointment newAppt(Appointment newAppointment, int userId) {
        int apptId = 0;
        String sql = "INSERT INTO appointments (appt_id, appt_date, start_time, end_time, appt_type, office_id, doctor_id, patient_note, patient_id, date_added)" +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, (SELECT patient_id FROM patient_accounts pa WHERE pa.user_id = ?), current_timestamp) returning appt_id;";

        apptId = jdbcTemplate.queryForObject(sql, Integer.class, newAppointment.getApptDate(), newAppointment.getStartTime(), newAppointment.getEndTime(),
                newAppointment.getApptType(), newAppointment.getOfficeId(), newAppointment.getDoctorId(),
                newAppointment.getPatientNote(), userId);

        newAppointment.setApptId(apptId);

        return newAppointment;
    }

    private Appointment mapRowToAppointment(SqlRowSet rs) {
        Appointment appt = new Appointment();
        appt.setApptId(rs.getInt("appt_id"));
        appt.setApptDate(rs.getString("appt_date"));
        appt.setStartTime(rs.getString("start_time"));
        appt.setEndTime(rs.getString("end_time"));
        appt.setApptType(rs.getString("appt_type"));
        appt.setOfficeId(rs.getInt("office_id"));
        appt.setPatientId(rs.getInt("patient_id"));
        appt.setDoctorId(rs.getInt("doctor_id"));
        appt.setPatientNote(rs.getString("patient_note"));
        appt.setDoctorNote(rs.getString("doctor_note"));
        appt.setDoctorName(rs.getString("doctor_name"));
        appt.setOfficeName(rs.getString("name"));
        appt.setPatientFirst(rs.getString("patient_first"));
        appt.setPatientLast(rs.getString("patient_last"));
        if (rs.getTimestamp("date_added") != null) {
            appt.setDateAdded(rs.getTimestamp("date_added").toLocalDateTime());
        }
        return appt;
    }

    private Appointment mapRowToAppointmentWithDayAge(SqlRowSet rs) {
        Appointment appt = new Appointment();
        appt.setApptId(rs.getInt("appt_id"));
        appt.setApptDate(rs.getString("appt_date"));
        appt.setStartTime(rs.getString("start_time"));
        appt.setEndTime(rs.getString("end_time"));
        appt.setApptType(rs.getString("appt_type"));
        appt.setOfficeId(rs.getInt("office_id"));
        appt.setPatientId(rs.getInt("patient_id"));
        appt.setDoctorId(rs.getInt("doctor_id"));
        appt.setPatientNote(rs.getString("patient_note"));
        appt.setDoctorNote(rs.getString("doctor_note"));
        appt.setDoctorName(rs.getString("doctor_name"));
        appt.setOfficeName(rs.getString("name"));
        appt.setPatientFirst(rs.getString("patient_first"));
        appt.setPatientLast(rs.getString("patient_last"));
        if (rs.getTimestamp("date_added") != null) {
            appt.setDateAdded(rs.getTimestamp("date_added").toLocalDateTime());
            appt.setDayAge(rs.getDouble("day_age"));
        }
        return appt;
    }
}
