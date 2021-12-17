package com.techelevator.model;

import java.util.Objects;

public class Review {

    private int reviewId;
    private int patientId;
    private int officeId;
    private String review;
    private String name;

    public Review() { }

    public Review(int reviewId, int patientId,int officeId, String review, String name) {
        this.reviewId = reviewId;
        this.patientId = patientId;
        this.officeId = officeId;
        this.review = review;
        this.name = name;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review that = (Review) o;
        return patientId == that.patientId && officeId == that.officeId && review.equals(that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, officeId, review);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId + '\'' +
                "patientId=" + patientId + '\'' +
                "officeId=" + officeId + '\'' +
                ", review=" + review + '}';
    }
}
