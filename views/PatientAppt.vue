<template>
  <div class="patient-appt">
    <patient-appt-detail />
  </div>
</template>

<script>
import PatientApptDetail from '../components/PatientApptDetail.vue';

export default {
  name: "patient-appt",
  components: {
    PatientApptDetail
  }
};
</script>

<style>
.patient-appt {
  display: flex;
  justify-content: center;
}
</style>
