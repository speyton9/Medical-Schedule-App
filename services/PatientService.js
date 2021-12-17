import axios from 'axios'; 

export default {
    getPatients(){
        return axios.get('/patients');
    },

    getEmailByPatientId(patientID){
        return axios.get(`/patients/${patientID}`);
    }


}
