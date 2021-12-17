import axios from 'axios';

export default {

    getDoctors() {
        return axios.get('/doctors');
    },

    getDoctorInfo(doctorID) {
        return axios.get(`/doctors/${doctorID}`);
    },

    updatedDoctorAvailability(updatedDoctorAccount, doctorID) {
        return axios.put(`/updateavailability/${doctorID}`, updatedDoctorAccount);
    },

    getDoctorByUserId(userId) {
        return axios.get(`doctor/${userId}`);
    }

}