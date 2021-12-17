<template>
<div class="outer-outer">
  <div class="UpdateOffice-Outer">
      <h2>Update Office Info</h2>
    <form v-on:submit.prevent="submitForm" class="updateOfficeForm">
      <label for="address">Update Address: </label>
      <input id="address" type="text" v-model="office.address" />
      <br />
      <label for="cityAddress">Update City: </label>
      <input id="cityAddress" type="text" v-model="office.cityAddress" />
      <br />
      <label for="stateAddress">Update State: </label>
      <input id="stateAddress" type="text" v-model="office.stateAddress" />
      <br />
      <label for="zipAddress">Update Zip: </label>
      <input id="zipAddress" type="text" v-model="office.zipAddress" />
      <br />
      <label for="phoneNumber">Update Office Phone: </label>
      <input id="phoneNumber" type="text" v-model="office.phoneNumber" />
      <br />
      <label for="startHour">Update Open Time: </label>
      <input id="startHour" type="text" v-model="office.startHour" />
      <br />
      <label for="endHour">Update Close Time: </label>
      <input id="endHour" type="text" v-model="office.endHour" />
      <br />
      <label for="price">Update Cost per hr: </label>
      <input id="price" type="number" v-model="office.price" />
      <br />
      <button type="submit" class="btn">Submit</button>
    </form>
  </div>
  </div>
</template>

<script>
import officeService from "@/services/OfficeService";
export default {
  name: "update-office",
  data() {
    return {
      office: {
        officeId: this.$route.params.officeID,
        address: "",
        cityAddress: "",
        stateAddress: "",
        zipAddress: "",
        startHour: "",
        endHour: "",
        phoneNumber: "",
        price: 0,
      },
    };
  },
  methods: {
    submitForm() {
      const newOffice = {
        officeId: this.office.officeId,
        address: this.office.address,
        cityAddress: this.office.cityAddress,
        stateAddress: this.office.stateAddress,
        zipAddress: this.office.zipAddress,
        startHour: this.office.startHour,
        endHour: this.office.endHour,
        phoneNumber: this.office.phoneNumber,
        price: this.office.price,
      };
      // update Office Info
      officeService
        .updateOffice(newOffice, this.office.officeId)
        .then(() => {

            // const savedAppointment = response.data;
            // this.$router.push({ name: "PatientAppt", params: { apptID: savedAppointment.apptId } });

            this.$router.push({ name: "officelistview"});
        });
    },
    created() {
      officeService.getOffice(this.$route.params.officeID).then((response) => {
        this.office = response.data;
        this.office.officeId = response.data.office.officeId;
      });
    },
  },
};
</script>

<style scoped>
.outer-outer {
  display: flex;
  /* padding: 5px; */
  justify-content: center;
  margin-top: 40px;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);
} 

.UpdateOffice-Outer {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  width: 90%;
  height: 100%;
  
}
.updateOfficeForm {
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
  width:  auto;
}

.btn {
    margin-left: 100px;
}

</style>