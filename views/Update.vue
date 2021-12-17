<template>
  <div class="flex">
    
    <doctor-update />

  </div>
</template>

<script>
import DoctorUpdate from '../components/DoctorUpdate.vue';

export default {
  name: "update-view",
  components: {
    DoctorUpdate,
  },
};
</script>

<style>

.flex {
  display: flex;
  justify-content: space-evenly;
  font-family: "Open Sans", sans-serif;
  -webkit-text-size-adjust: 100%;
  background: #fff;
  margin: 0;
  padding: 20pt;
  color: #2B2F33;
  border-width: thick;
}

</style>