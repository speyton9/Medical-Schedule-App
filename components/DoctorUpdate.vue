<template>
  <div class="success-check">
    <div class="update-bubble">
      <h2>Update Your Availbility</h2>
      <form v-on:submit.prevent="submitForm" class="updateDoctorForm">

        <div class="status-message error" v-show="errorMsg !== ''">
          {{ errorMsg }}
        </div>
        <br />
        <!-- Gets the doctor's current availability here -->
        <div class="hours">
         Current Availability: {{ doctor.clockIn }} to {{ doctor.clockOut }}
        </div>
        

        <label for="clockIn">Available From: </label>
        <input
          id="clockIn"
          type="text"
          v-model="updatedDoctor.clockIn"
          autocomplete="off"
          class="form-control"
          placeholder="Time HH:MM AM or AM"
        />
        <br />
        <label for="clockOut">Available To: </label>
        <input
          id="clockOut"
          type="text"
          v-model="updatedDoctor.clockOut"
          autocomplete="off"
          class="form-control"
          placeholder="Time HH:MM AM or PM"
        />
        <br />

        <button class="btn btn-submit m-1" type="submit">Submit</button>
        
        <button
          class="btn btn-cancel"
          type="cancel"
          v-on:click.prevent="cancelForm()"
        >
          Cancel
        </button>


      </form>
    </div>
  </div>
</template>

<script>
import doctorService from "@/services/DoctorService";
export default {
  name: "doctor-update",
  data() {
    return {
      updatedDoctor: {
        firstName: "",
        lastName: "",
        clockIn: "",
        clockOut: "",
        email: "",
        userId: "",
      },
      //added stuff here
      doctor: {
        firstName: "",
        lastName: "",
        clockIn: "",
        clockOut: "",
        email: "",
        userId: "",
      },
      errorMsg: "",
    };
  },
  methods: {
    submitForm() {
      const updatedDoctorAccount = {
        firstName: this.updatedDoctor.firstName,
        lastName: this.updatedDoctor.lastName,
        clockIn: this.updatedDoctor.clockIn,
        clockOut: this.updatedDoctor.clockOut,
        email: this.updatedDoctor.email,
        userId: this.updatedDoctor.userId,
      };
      doctorService.updatedDoctorAvailability(updatedDoctorAccount, this.updatedDoctor.id)
        .then((response) => {
          if (response.status === 200) {
            this.$router.push({ name: "officelistview" });
          }
        });  
    },
    cancelForm() {
      this.$router.push({ name: "officelistview" });
    },
  },
  created() {
    //Gets the object for user to update
    doctorService
      .getDoctorByUserId(this.$store.state.user.id)
      .then((response) => {
        this.updatedDoctor = response.data;
      });
    //Gets the object to display current availability  
    doctorService
      .getDoctorByUserId(this.$store.state.user.id)
      .then((response) => {
        this.doctor = response.data;
      });
  },
};
</script>

<style scoped>

.success-check {
  display: flex;
  padding: 5px;
  justify-content: center;
}

.update-bubble {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  /* width: 100%;
  height: 100%; */
  text-align: center;
}
.updateDoctorForm {
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}

textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}
select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}

.btn-submit {
  color: #fff;
  background-color: #0062cc;
  border-color: #005cbf;
  margin-left: 70px;
}

.btn-cancel {
  color: #fff;
  background-color: #3498db;
  border-color: #dc3545;
}

.form-control {
  background: #ecf0f1;
  border: #ccc 1px solid;
  border-bottom: #ccc 2px solid;
  padding: 8px;
  width: 300px;
  color: black;
  margin-top: 10px;
  font-size: 1em;
  border-radius: 4px;
}
</style>