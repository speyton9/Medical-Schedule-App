<template>
<div class="outside">
  <div class="outer">
      <!--Changing Patient Details to name and email here -->
       <h3 class="title">Patients Details</h3>
       <div>Email: {{email}}</div>
       <br>
      <div class="patient" v-for="patient in patients" v-bind:key="patient.id">
          <h4>{{patient.patientFirst}} 
      {{patient.patientLast}}</h4>
  <div class="patientdetail">  
     <span><strong>Appointment Date</strong><br> {{patient.apptDate}}</span>
     <br>
     <br>
     <div><strong>Check up Time</strong> <br><br> {{patient.startTime }} to {{ patient.endTime}}</div>
      <div><strong>Appointment Type</strong> <br> {{patient.apptType}}
    </div>
      </div>
     <h4>Doctor details</h4> 
      <div class="doctordetail">
  
     Doctor Name : {{patient.doctorName}}<br>
      <div>Location : {{patient.officeName}}</div>
 


  </div>

 </div>
  </div>
</div>
</template>


<script>
import apptService from "@/services/ApptService";
import patientService from "@/services/PatientService";
export default {
    name: 'patient-detail',
    data (){
        return{
            patients:[],
            email: "",

        }

    },
    created(){
        apptService.getApptByPatient(this.$route.params.patientID).then(Response=>{
            this.patients=Response.data;
        }),

        patientService.getEmailByPatientId(this.$route.params.patientID).then(response=>{
            this.email = response.data;
        });
    },
   

}
</script>

<style scoped>
.outside{
    display: flex;
    flex-direction: column;
    justify-content: center;

}
.patient{
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);
  margin-bottom: 10px;
  cursor: pointer;
    

}
.outer {
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-color: #f7fafc;
    border-radius: 10px;
    padding: 20px 20px 20px 20px;
    text-align: center;
    flex-grow: 1;
    margin: 20px 20px;
    margin-left: 420px;
    
    
}
.patient:hover{
    color: rgb(62, 100, 226);
    border: 2px solid rgb(72, 128, 21);
}
.patientdetail{
  display: flex;
  justify-content: space-between;

}

.doctordetail{
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 0.8rem;
}





</style>