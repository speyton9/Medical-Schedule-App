<template>
  <div class="outer" >
  <div class="appt-detail">
    <h1>Appointment Details</h1>
    <div class="appt">
      <div class="header">
        <h3>Date of Appointment: {{ appt.apptDate }}</h3>
      </div>
      <div class="footer">
        <span class="address">
          <br>
          <strong>You'll be seeing Dr.{{ appt.doctorName }}</strong>
          <br>
          at {{ appt.officeName }}
          <br>
          <strong>Appointment Type:<br></strong>{{ appt.apptType }}
        </span>
        <span class="hours">
          <strong>Time of Appointment:</strong>
          <br>{{ appt.startTime }}
        </span><div>
        <strong>Reason for Appointment:</strong>
        <br>        
        {{ appt.patientNote }}</div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import apptService from "@/services/ApptService";

export default {
  name: "patient-appt-detail",
  data() {
    return {
      appt: {},
    };
  },
  created() {
    apptService.getAppt(this.$route.params.apptID).then((response) => {
      this.appt = response.data;
    });
  },
};
</script>
<style scoped>
.title>h3 {
  font-size: .7rem;
}
</style>
<style>
.outer {
  display: flex;
  justify-content: center;
  width: 475px;
}

.appt-detail {
  /* padding: 20px 20px; */
  /* margin: 0 auto;
  max-width: 600px; */
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  /* justify-content: center; */
  background: #fff;
  border: 1px;
  margin-bottom: 10px;
  cursor: pointer;
  border-radius: 6px;
  margin: 70px auto 0 auto;
  padding: 10px 10px 20px 10px;
  font-size: 10px !important;
  font-family: "Open Sans", sans-serif;;
}
.appt {
  /* font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px; */
  box-shadow: 0 10px 10px 0 rgba(0, 0, 0, .5), 0 1px 2px 0 rgba(0, 0, 0, .6);
  border-radius: 0.25rem;
  padding: 10 px 20px;
  border: 1px;
  margin-bottom: 10px;
  cursor: pointer;
  
     /* text-align: center; */
}
.appt:last-child {
  /* border: 0px; */
  margin-bottom: 0px;
}

.appt h3 {
  margin-top: 0px;
  font-size: 0.875rem;

}
.appt .header {
  display: flex;
 
  align-items: center;
  justify-content: center;
}

.appt .footer {
  /* display: flex; */
  /* align-items: center; */
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 1rem !important;
  
 
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