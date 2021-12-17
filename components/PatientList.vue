<template>
<div>

  <div class="Patient-list">
    <h2>Patients</h2>
    <div class="Patient">
      <div
        class="patient"
        v-for="patient in patients"
        v-bind:key="patient.id"
        v-on:click="getApptByPatient(patient.id)"
      >
        <div class="patient-name">
          <h3>{{ patient.firstName }}
              {{ patient.lastName }}
          </h3>
        
          <span class="email">{{ patient.email }}
          </span>
        </div>
      </div>
    </div>
  </div>
</div>
   
</template>

<script>
import patientService from '@/services/PatientService'
export default {
  name:'patient-list',
  data (){
    return {
      patients :[]
    }
  },
  created(){
    patientService.getPatients().then((Response)=>{
      this.patients=Response.data;
    })
  },
   methods: {
    getApptByPatient(patientID) {
      this.$router.push({ name: "patientDetail",params:{patientID}})
    },
  },
  

}
</script>

<style scoped>
.outer {
  display: flex;
  justify-content: center;
  width: 475px;
 
}

.Patient-list {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  /* width: 100%; */
  flex-grow: 1;
  font-size: 1rem;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);
}
.patient{
  /* font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px; */
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%) !important;
  margin-bottom: 10px;
  cursor: pointer;
  font-size: 1rem;
}
.patient:last-child {
  /* border: 0px; */
  margin-bottom: 0px;
}

.patient h3 {
  margin-top: 0px;
  font-size: 0.875rem;
}

.patient-name {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  /* color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%); */

}


.patient:hover {
    color: rgb(62, 100, 226);
    border: 2px solid rgb(72, 128, 21);
}





</style>