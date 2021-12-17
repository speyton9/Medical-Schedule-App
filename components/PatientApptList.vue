<template>

  <div class="outer">
    <div class="patient-appt-list">
      <h2>Appointments</h2>
      <h4>Appointments In Green Are New</h4>
      <div class="appointments">
        <div
          class="appt"
          v-for="appt in appts"
          v-bind:key="appt.id"
          v-on:click="viewApptDetails(appt.apptId)"
          v-bind:class="{ greenCard: cardColorCheck(appt)}"
        >
          <div class="header-date">
            {{ appt.apptDate }}
          </div>
          <div class="footer">
            <span class="address">
              <br />
              Dr. {{ appt.doctorName }}
              <br />
              {{ appt.officeName }}
              <br />
              {{ appt.apptType }}
            </span>
            <span class="hours">
              {{ appt.startTime }}

              <!-- Testing -->

            </span>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import apptService from "../services/ApptService";


export default {
  name: "patient-appt-list",
  data() {
    return {
      appts: [],
      // user: {},
    };
  },
  created() {
    apptService.getAppts(this.$store.state.user.id).then((response) => {
      this.appts = response.data;
    });
  },
  methods: {
    viewApptDetails(apptID) {
      this.$router.push({ name: "PatientAppt", params: { apptID: apptID } });
    },
    diffInDays(card) {
      if (card.dateAdded == '2000-12-05T10:00:00') {
        return 100;
      }

      // const date1 = Date.now();
      const date1 = Math.floor((new Date()).getTime() / 1000);
    
      let newDate2 = card.dateAdded;
      // let newDate2 = card;
      const index = newDate2.lastIndexOf(".");
      if (index >= 0) {
        newDate2 = newDate2.substring(0, index)
        console.log(date1);
        newDate2 = newDate2 + "Z";
        
      }

      const date2 = Date.parse(newDate2);
      console.log(date2);


      // One day in milliseconds
      const oneDay = 1000 * 60 * 60 * 24;

      // Calculating the time difference between two dates
      // const diffInTime = date2.getTime() - date1.getTime();
      const diffInTime = date1 - date2;

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
    
    }
  },
};
</script>

<style >



.greenCard {
  background: rgb(6, 240, 6) !important;
}
.outer {
  display: flex;
  justify-content: center;
  width: 475px;
  /* flex-grow: 1; */
}
.address,.hours{
  font-size: 1rem;
}
.patient-appt-list {
  /* padding: 20px 20px;
  margin: 0 auto;
  max-width: 600px; */
  display: flex;
  flex-direction: column;
  background-color: #F7FAFC;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  /* width: 100%; */
  flex-grow: 1;
}
.appt {
  /* font-size: 24px;
  border-bottom: 1px solid #F2F2F2;
  padding: 10px 20px; */
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
  font-size: 1.2rem;
}
.appt:last-child {
  /* border: 0px; */
  margin-bottom: 0px;
}
.header-date {
  margin-top: 0px;
  font-size: 2rem;
}
.appt .header {
  display: flex;
  justify-content: space-between;
  font-size: 1.2rem;
}
.appt .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 1.2rem;}
  </style>