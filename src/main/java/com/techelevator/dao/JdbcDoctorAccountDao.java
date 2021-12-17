package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.FirstNameNotFoundException;
import com.techelevator.model.LastNameNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.DoctorAccount;



@Service
public class JdbcDoctorAccountDao implements DoctorAccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcDoctorAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findAccountIdByFirstName(String firstName) {
        return jdbcTemplate.queryForObject("select doctor_id from doctor_accounts where first_name = ?", int.class, firstName);
    }

    @Override
    public DoctorAccount getAccountById(int accountId) {
        String sql = "SELECT * FROM doctor_accounts WHERE doctor_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if(results.next()) {
            return mapRowToDoctorAccount(results);
        } else {
            throw new RuntimeException("accountId "+accountId+" was not found.");
        }
    }

    @Override
    public DoctorAccount getAccountByDoctorId(int doctorId) {
        String sql = "SELECT * FROM doctor_accounts WHERE doctor_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        if(results.next()) {
            return mapRowToDoctorAccount(results);
        } else {
            throw new RuntimeException("doctorId "+doctorId+" was not found.");
        }
    }

    @Override
    public List<DoctorAccount> findAll() {
        List<DoctorAccount> doctorAccounts = new ArrayList<>();
        String sql = "select * from doctor_accounts";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            DoctorAccount doctorAccount  = mapRowToDoctorAccount(results);
            doctorAccounts.add(doctorAccount);
        }

        return doctorAccounts;
    }

    @Override
    public DoctorAccount findByFirstName(String firstname) throws FirstNameNotFoundException {
        for (DoctorAccount doctorAccount : this.findAll()) {
            if(doctorAccount.getFirstName().toLowerCase().equals(firstname.toLowerCase())) {
                return doctorAccount;
            }
        }
        throw new FirstNameNotFoundException("Doctor first name " + firstname + " was not found.");
    }
    @Override
    public DoctorAccount findByLastName(String lastname) throws LastNameNotFoundException {
        for (DoctorAccount doctorAccount : this.findAll()) {
            if(doctorAccount.getLastName().toLowerCase().equals(lastname.toLowerCase())) {
                return doctorAccount;
            }
        }
        throw new LastNameNotFoundException("Doctor last name " + lastname + " was not found.");
    }

    //This method shows the availability in terms of when they clockIn/clockOut
    @Override
    public String viewAvailabilityByDoctor(String firstName, String lastName) {

        String sql = "SELECT clock_in, clock_out FROM doctor_accounts" +
                " WHERE do_first_name ILIKE '%'?'%' AND do_last_name ILIKE '%'?'%'";

        String results = jdbcTemplate.queryForObject(sql, String.class, firstName, lastName);

        return results;
    }

    @Override
    public void updateAvailability(DoctorAccount doctorAccount, int doctorId){
        //Updates the clock in and clock out times based on doctor name
        //input parameters will be inputted in view in pretty sure
        String sql = "UPDATE doctor_accounts SET clock_in = ?, clock_out = ?" +
                " WHERE doctor_id = ?";

        jdbcTemplate.update(sql, doctorAccount.getClockIn(), doctorAccount.getClockOut(), doctorId);
    }

    @Override
    public DoctorAccount getAccountByUserId(int userId) {
        DoctorAccount doctorAccount = new DoctorAccount();
        String sql = "SELECT doctor_id, first_name, last_name, clock_in, clock_out, email FROM doctor_accounts" +
                " JOIN users ON users.user_id = doctor_accounts.user_id" +
                " WHERE users.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            doctorAccount =  mapRowToDoctorAccount(results);
        }
        return doctorAccount;
    }

    @Override
    public boolean create(String firstName, String lastName, String email, String username) {

        // create account
        String insertDoctorAccount = "insert into doctor_accounts (first_name,last_name,email, user_id) values(?,?,?, (select user_id from users WHERE username = ?))";

        jdbcTemplate.update(insertDoctorAccount, firstName, lastName, email, username);

        return true;

    }

    private DoctorAccount mapRowToDoctorAccount(SqlRowSet rs) {
        DoctorAccount  doctorAccount= new DoctorAccount();
        doctorAccount.setId(rs.getInt("doctor_id"));
        doctorAccount.setFirstName(rs.getString("first_name"));
        doctorAccount.setLastName(rs.getString("last_name"));
        doctorAccount.setClockIn(rs.getString("clock_in")); //Getters here instead of strings???
        doctorAccount.setClockOut(rs.getString("clock_out")); //for clockin/clockout??
        doctorAccount.setEmail(rs.getString("email"));
        return doctorAccount;
    }
}
