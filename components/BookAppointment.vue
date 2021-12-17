<template>
  <div class="success-check" >
    <div class="appt-bubble" >
      <h2>Book an Appointment</h2>
      <form v-on:submit.prevent class="apptForm">
        <div class="status-message error" v-show="errorMsg !== ''">
          {{ errorMsg }}
        </div>

        <label for="apptDate"></label>
        <input
          id="apptDate"
          type="text"
          v-model="appt.apptDate"
          autocomplete="off"
          class="form-control"
          placeholder="Date MM-DD-YYYY"
        />
        <br />

        <label for="apptType">Select Appt Type: </label>
        <select
          id="apptType"
          v-model="appt.apptType"
          class="form-control"
          placeholder="Select Type"
        >
          <option value="In-Person">In-Person</option>
          <option value="Remote">Remote</option>
        </select>
        <br />
        <label for="doctorId">Select a Doctor:</label>
        <select
          id="doctorId"
          v-model="appt.doctorId"
          class="form-control"
          placeholder="Select a Doctor"
        >
          <option
            v-for="doctor in doctors"
            :key="doctor.id"
            v-bind:value="doctor.id"
          >
            {{ doctor.lastName }}
          </option>
        </select>
        <br />
        <label for="officeId">Select an Office:</label>
        <select
          id="officeId"
          v-model="appt.officeId"
          class="form-control"
          placeholder="Select an Office"
        >
          <option
            v-for="office in offices"
            :key="office.id"
            v-bind:value="office.id"
          >
            {{ office.name }}
          </option>
        </select>
        <br />

        <label for="startTime"> </label>
        <input
          id="startTime"
          type="text"
          v-model="appt.startTime"
          autocomplete="off"
          class="form-control"
          placeholder="Start Time HH:MM AM or PM"
        />
        <br />
        <label for="endTime"></label>
        <input
          id="endTime"
          type="text"
          v-model="appt.endTime"
          autocomplete="off"
          class="form-control"
          placeholder="End Time HH:MM AM or PM"
        />
        <br />
        <label for="patientNote"></label>
        <textarea
          id="patientNote"
          type="text"
          v-model="appt.patientNote"
          autocomplete="off"
          class="form-control"
          placeholder="Reason for Making Appointment"
        />
        <br />

        <button
          class="btn btn-submit m-1"
          type="submit"
          v-on:click="submitForm()"
        >
          Submit
        </button>
        <button
          class="btn btn-cancel"
          type="cancel"
          v-on:click.prevent="cancelForm"
        >
          Cancel
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import apptService from "../services/ApptService";
import doctorService from "../services/DoctorService";
import officeService from "../services/OfficeService";
// import patientService from "../services/PatientService";

export default {
  name: "book-appointment",

  data() {
    return {
      savingSuccessful: false,
      appt: {
        apptDate: "",
        apptType: "",
        doctorId: "",
        startTime: "",
        endTime: "",
        officeId: "",
        patientId: "",
        patientNote: "",
      },
      doctors: [],
      offices: [],
      errorMsg: '',
    };
  },
  methods: {
    submitForm() {
      const newAppt = {
        apptDate: this.appt.apptDate,
        apptType: this.appt.apptType,
        doctorId: this.appt.doctorId,
        startTime: this.appt.startTime,
        endTime: this.appt.endTime,
        officeId: this.appt.officeId,
        // patientId: this.$store.state.user.id,
        patientNote: this.appt.patientNote,
      };
      // add new appointment
      apptService
        .addAppt(newAppt, this.$store.state.user.id)
        .then((response) => {
          const savedAppointment = response.data;
          this.$router.push({ name: "PatientAppt", params: { apptID: savedAppointment.apptId } });
          this.savingSuccessful = true;
          // if (response.status === 201) {
          //   const savedAppointment = response.data;
          //   this.$router.push({ name: "PatientAppt", params: { apptID: savedAppointment.apptId } });
          // // this.savingSuccessful = true;
          // }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
    },
    cancelForm() {
      this.$router.push({ name: "Home" });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " appointment. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " appointment. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " appointment. Request could not be created.";
      }
    },
  },
  created() {
    doctorService.getDoctors().then((response) => {
      this.doctors = response.data;
    });
    officeService.getOffices().then((response) => {
      this.offices = response.data;
    });
  },
};
</script>


<style >
.success-check {
  display: flex;
  padding: 5px;
  justify-content: center;
}

.appt-bubble {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  width: 100%;
  height: 575px;
  text-align: center;
}
.apptForm {
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
