import axios from 'axios';

export default {
    getReview() {
        return axios.get('/reviews');
    },

    getReviews(officeID) {
        return axios.get(`/reviews/${officeID}`);
    },

    // Need officeID as well?
    addReview(newReview, officeID) {
        return axios.post(`/review/${officeID}`, newReview);
    }
}