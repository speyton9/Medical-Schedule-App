<template>
  <div class="doctor">
    <h1>Doctors In Your Area</h1>
    <doctor-list />
  </div>
</template>

<script>
import DoctorList from '../components/DoctorList.vue';

export default {
  name: "doctor",
  components: {
    DoctorList
  }
};
</script>