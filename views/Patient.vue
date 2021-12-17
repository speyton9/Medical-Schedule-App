<template>
<div class="patient-outer">
    <!-- <h1>Welcome to the Patient Portal</h1> -->
    
  <patient-list/>
</div>

</template>

<script>
import PatientList from '../components/PatientList.vue'
export default {
  components: { PatientList },

}
</script>

<style>
.patient-outer{
  display: flex;
  justify-content: center ;

}
 

</style>