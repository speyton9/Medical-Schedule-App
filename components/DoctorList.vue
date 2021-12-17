<template>
  <div class="doctor-list">
    <h2>Doctors</h2>
    <div class="doctors">
      <div
        class="doctor"
        v-for="doctor in doctors"
        v-bind:key="doctor.id"
        v-on:click="viewDoctorInfo(doctor.id)"
      >
        <div class="header">
          <h3>{{ doctor.firstName }}
              {{ doctor.lastName }}
          </h3>
        </div>
          <span class="email"><p>{{ doctor.email }}</p>
          </span>
          <span class="hours">
            {{ doctor.clockIn }}
            to
            {{ doctor.clockOut }}
          </span>
      </div>
    </div>
  </div>
</template>

<script>
import doctorService from "../services/DoctorService";

export default {
  name: "doctor-list",
  data() {
    return {
      doctors: [],
    };
  },
  created() {
    doctorService.getDoctors().then((response) => {
      this.doctors = response.data;
    });
  },
  methods: {
    viewDoctorInfo(doctorID) {
      this.$router.push({ name: "DoctorDetails", params: { doctorID: doctorID } });
    },
  },
};
</script>



<style scoped>
.outer {
  display: flex;
  justify-content: center;
  width: 400px;
}

.doctor-list{
  /* padding: 20px 20px;
  margin: 0 auto;*/
  max-width: 600px; 
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center; 
  background-color: #fff;
  border-radius: 10px;
  padding: 0px 20px 20px 20px;
  text-align: center;
  /* align-items: center; */
  flex-grow: 1;
  position: relative;
  left: 315px;
  

}
.doctor {
  /* font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px; */
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 10px 10px 0 rgba(0, 0, 0, 0.5), 0 10px 10px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;

 
}

/* .doctor { */
  /* font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px; */
  /* background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 10px 10px 0 rgba(0, 0, 0, 0.5), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
  max-width: 600px; 
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center; */

  /* border-radius: 10px;
  padding: 0px 20px 20px 20px;
  text-align: center; */
  /* align-items: center; */
 

  
 

.header {
 
  background: #fff;
  border-radius: 0.25rem;  
  margin-bottom: 10px;
  cursor: pointer;
  font-size: 10px;
  justify-content: space-around;

}


.doctor:last-child {
  /* border: 0px; */
  margin-bottom: 0px;
}


.doctor p {
  margin-top: 0px;
  font-size: 1rem;
}

  
.doctor h3 {
  margin-top: 0px;
  font-size: 0.875rem;
}
/* 
.doctor p {
  margin-top: 0px;
  font-size: 0.875rem;
} */
/* .doctor .header {
  display: flex;
  justify-content: space-around;
  
}

.doctor .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 0.75rem;
} */
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

.doctors{
 color: gray;
box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);
}
</style>


