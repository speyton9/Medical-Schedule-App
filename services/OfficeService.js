import axios from 'axios';

export default {

    getOffices() {
        return axios.get('/offices');
    },

    getOffice(officeID) {
        return axios.get(`/offices/${officeID}`);
    },

    updateOffice(newOffice, officeID) {
        return axios.put(`/offices/${officeID}`, newOffice);
    }
}