<template>
<div class="doctor-outer">
  <div class="doctor-appt-list">
    <h2>Appointments</h2>
    <h4>Appointments In Green Are New</h4>
    <div class="appointments">
      <div
        class="appt"
        v-for="appt in appts"
        v-bind:key="appt.id"
        v-on:click="viewApptDetails(appt.apptId)"
        v-bind:class="{ greenCard: cardColorCheck(appt) }"
      >
        <div class="header">
          <h2>{{ appt.apptDate }}</h2>
        </div>
        <div class="footer">
          <span class="address">
            <br />
            {{ appt.patientFirst }}
            {{ appt.patientLast }}
            <br />
            {{ appt.officeName }}
            <br />
            {{ appt.apptType }}
          </span>
          <span class="hours">
            {{ appt.startTime }}
          </span>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import apptService from "../services/ApptService";
// import doctorService from "../services/DoctorService";
// import officeService from "../services/OfficeService";

export default {
  name: "patient-appt-list",
  data() {
    return {
      appts: [],
      // doctors: [],
      // offices: [],
      user: {},
    };
  },
  created() {
    // apptService.getAllAppts().then((response) => {
    //   this.appts = response.data;
    // });
    // doctorService.getDoctors().then((response) => {
    //   this.doctors = response.data;
    // });
    apptService.getDocAppts(this.$store.state.user.id).then((response) => {
      this.appts = response.data;
    });
    // officeService.getOffices().then((response) => {
    //   this.offices = response.data;
    // });
  },
  methods: {
    viewApptDetails(apptID) {
      this.$router.push({ name: "PatientAppt", params: { apptID: apptID } });
    },
    diffInDays(card) {
      const date1 = Date.now();

      let newDate2 = card.dateAdded;
      // let newDate2 = card;
      const index = newDate2.lastIndexOf(".");
      if (index >= 0) {
        newDate2 = newDate2.substring(0, index);
        console.log(newDate2);
        newDate2 = newDate2 + "Z";
        console.log(newDate2);
      }

      const date2 = Date.parse(newDate2);

      // One day in milliseconds
      const oneDay = 1000 * 60 * 60 * 24;

      // Calculating the time difference between two dates
      // const diffInTime = date2.getTime() - date1.getTime();
      const diffInTime = date2 - date1;

      // Calculating the no. of days between two dates
      const diffInDays = Math.round(diffInTime / oneDay);

      return diffInDays;
    },
    cardColorCheck(card) {
      // const dayCheck = this.diffInDays(card);
      const dayCheck = card.dayAge;
      if (dayCheck != null && dayCheck <= 1) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style >
.doctor-outer{
   /* padding: 20px 20px;*/
  margin: 20px;
  max-width: 30%; 
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* text-align: center; */
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0px 20px 20px 20px;
  text-align: center;
  /* align-items: center; */
  flex-grow: 1;

  
}

.greenCard {
  background: rgb(6, 240, 6) !important;
}

.outer {
  display: flex;
  justify-content: center;
  width: 475px;
  
  /* flex-grow: 1; */
}

.patient-appt-list {
  /* padding: 20px 20px;
  margin: 0 auto;
  max-width: 600px; */
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  width: 20%;
}
.appt {
  /* font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px; */
  background: #fff;
  border-radius: 0.25rem;
  /* padding: 10px; */
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}
.appt:last-child {
  /* border: 0px; */
  margin-bottom: 0px;
}

.appt>h3 {
  margin-top: 0px;
  font-size: 1.5rem;
}
.appt .header {
  display: flex;
  justify-content: space-between;
}

.appt .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 0.75rem;
}
/* .pill {
  padding: 8px;
  border-radius: 20px;
  font-size: 0.7rem;
}
.design {
  background-color: #faf5ff;
  color: #6b46c1;
}
.qa {
  background-color: #f0fff4;
  color: #2c7a7b;
}

.feature {
  background-color: #e6fffa;
  color: #2c7a7b;
} */
</style>