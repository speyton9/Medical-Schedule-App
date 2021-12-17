<template>
  <div class="appts-list">
    <patient-appt-list v-show="$store.state.isDoctor===false" />
    <doctor-appt-list v-show="$store.state.isDoctor===true" />
  </div>
</template>

<script>
import PatientApptList from '@/components/PatientApptList'
import DoctorApptList from '@/components/DoctorApptList'

export default {
  name: "appts-view",
  components: {
    DoctorApptList,
    PatientApptList
  }
};
</script>

<style>
.appts-list {
  display: flex;
  justify-content: center;
}
</style>
