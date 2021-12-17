<template>
  <div class="home">
    <h1>Home</h1>
    <!-- <p>You must be authenticated to see this</p> -->
    <div class="flex">
      <!-- <office-list /> -->
      <patient-appt-list v-show="$store.state.isDoctor===false" />
      <doctor-appt-list v-show="$store.state.isDoctor===true" />
      <book-appointment v-show="$store.state.isDoctor===false" />
      <doctor-update v-show="$store.state.isDoctor===true" />
    </div>
    <!-- <book-appointment /> -->
  </div>
</template>

<script>
import BookAppointment from "../components/BookAppointment.vue";
import DoctorApptList from '../components/DoctorApptList.vue';
import DoctorUpdate from '../components/DoctorUpdate.vue';
// import OfficeList from "../components/OfficeList.vue";
import PatientApptList from "../components/PatientApptList";

export default {
  name: "home",
  components: {
    // OfficeList,
    PatientApptList,
    BookAppointment,
    DoctorApptList,
    DoctorUpdate,
  },
};
</script>

<style >

.flex {
 display: flex;
  justify-content: space-evenly;
  font-family: "Lato", -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  -webkit-text-size-adjust: 100%;
  background: #fff;
  margin: 0;
  padding: 20pt;
  color: #2B2F33;
}
.office-list,.patient-appt-list{
  background: #fff;
}

.offices>div,.appointments>div,.appt-bubble,.update-bubble{
  
 /* border: 2px solid rgb(72, 128, 21);*/
  color: gray;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, .5), 0 6px 20px 0 rgba(0, 0, 0, .5);
}
.offices>div:hover,.appointments>div:hover,.appt-bubble:hover{
    color: rgb(62, 100, 226);
    border: 2px solid rgb(72, 128, 21);
}

.appt-bubble{
  display: block;
  margin-left:auto;
  margin-right: auto;
}
.appt-bubble>h2,.update-bubble>h2{
  color: rgb(62, 112, 33);
}
/*
.home{
    display: flex;
    -moz-align-items: stretch;
    align-items: stretch;
    list-style: none;
    margin: 0;
    padding: 0;
}
 */
  nav ul li a,
 nav ul li a.router-link-active,
 nav ul li a.router-link-exact-active {
 text-decoration: none;
 font-weight: bold;
 font: outline;
 }

</style>