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

import com.techelevator.model.PatientAccount;

@Service
public class JdbcPatientAccountDao implements PatientAccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPatientAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findAccountIdByFirstName(String firstName) {
        return jdbcTemplate.queryForObject("select patient_id from patient_accounts where first_name = ?", int.class, firstName);
    }

    @Override //This one
    public String getEmailByPatientId(Long id) {
        String sql = "SELECT email FROM patient_accounts WHERE patient_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    @Override
    public List<PatientAccount> findAll() {
        List<PatientAccount> patientAccounts = new ArrayList<>();
        String sql = "select * from patient_accounts";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            PatientAccount patientAccount = mapRowToPatientAccount(results);
            patientAccounts.add(patientAccount);
        }

        return patientAccounts;
    }

    @Override
    public PatientAccount findByFirstName(String firstname) throws FirstNameNotFoundException {
        for (PatientAccount patientAccount : this.findAll()) {
            if( patientAccount.getFirstName().toLowerCase().equals(firstname.toLowerCase())) {
                return patientAccount;
            }
        }
        throw new FirstNameNotFoundException("First Name " + firstname + " was not found.");
    }

    @Override
    public PatientAccount findByLastName(String lastname) throws LastNameNotFoundException {
        for (PatientAccount patientAccount : this.findAll()) {
            if( patientAccount.getLastName().toLowerCase().equals(lastname.toLowerCase())) {
                return patientAccount;
            }
        }
        throw new LastNameNotFoundException("Last Name " + lastname + " was not found.");
    }

    @Override
    public boolean create(String firstName, String lastName, String email, String username) {

        // create account
        String insertPatientAccount = "insert into patient_accounts (first_name, last_name,email, user_id) values(?,?,?, (select user_id from users WHERE username = ?))";

        jdbcTemplate.update(insertPatientAccount, firstName, lastName, email, username);

        return true;

    }

    private PatientAccount mapRowToPatientAccount(SqlRowSet rs) {
        PatientAccount patientAccount = new PatientAccount();
        patientAccount.setId(rs.getLong("patient_id"));
        patientAccount.setFirstName(rs.getString("first_name"));
        patientAccount.setLastName(rs.getString("last_name"));
        patientAccount.setEmail(rs.getString("email"));
        // user.setActivated(true);
        return patientAccount;
    }
}

