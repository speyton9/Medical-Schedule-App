import axios from 'axios';

export default {

    getAllAppts() {
        return axios.get('/appts');
    },

    // Used to get Patients' appointments in PatientApptList
    getAppts(userID) {
        return axios.get(`/appts/${userID}`);
    },

    // Used to get Doctors' appointments in DoctorApptList
    getDocAppts(userID) {
        return axios.get(`/docappts/${userID}`);
    },

    // Used to get Appointment Details in PatientApptDetail
    getAppt(apptID) {
        return axios.get(`/appts/${apptID}/details`);
    },

    // Used to post a new Appointment in BookAppointment
    addAppt(newAppt, userID) {
        return axios.post(`/appts/${userID}`, newAppt);
    },
    // Used in Patient Details component
    getApptByPatient(patientID) {
        return axios.get(`/appts/patient/${patientID}`);
    }


}