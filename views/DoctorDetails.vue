<template>
  <div class="doctor">
    <h1>Your Doctor</h1>
    <doctor-info />
  </div>
</template>

<script>
import DoctorInfo from '../components/DoctorInfo.vue';

export default {
  name: "doctor-details",
  components: {
    DoctorInfo
  }
};
</script>