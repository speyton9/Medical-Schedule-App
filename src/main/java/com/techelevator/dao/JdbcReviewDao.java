package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.model.Review;

@Component
public class JdbcReviewDao implements ReviewDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> findAll(int officeId) {
        List<Review> reviews = new ArrayList<>();
//        String sql = "SELECT * FROM reviews WHERE office_id = ?";
          String sql = "select pa.first_name AS name, r.review_id, r.patient_id, r.office_id, r.review from reviews r\n" +
                  "Join patient_accounts pa ON pa.patient_id = r.patient_id WHERE office_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, officeId);
        while(results.next()) {
            Review review = mapRowToReview(results);
            reviews.add(review);
        }

        return reviews;
    }

    @Override
    public boolean create(int patientId, int officeId, String review) {

        // create review
        String insertReview = "insert into reviews (patient_id, office_id, review) values (?,?,?)";

        jdbcTemplate.update(insertReview, patientId, officeId, review);

        return true;

    }

    @Override
    public Review newReview(Review newReview, int userId) {
        int reviewId = 0;
        String sql = "INSERT INTO reviews (review_id, patient_id, office_id, review)" +
                "VALUES (DEFAULT, (SELECT patient_id FROM patient_accounts WHERE user_id = ?), ?, ?) returning review_id;";

        reviewId = jdbcTemplate.queryForObject(sql, Integer.class, userId, newReview.getOfficeId(), newReview.getReview());

        newReview.setReviewId(reviewId);

        return newReview;
    }

    private Review mapRowToReview(SqlRowSet rs) {

        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setPatientId(rs.getInt("patient_id"));
        review.setOfficeId(rs.getInt("office_id"));
        review.setReview(rs.getString("review"));
        review.setName(rs.getString("name"));

        return review;
    }
}