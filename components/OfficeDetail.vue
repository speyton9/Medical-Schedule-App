<template>
  <div class="office-info">
    <h1>Office Details</h1>
    <span class="office_name">{{ office.name }}</span>
    <h3>Address</h3>
    <div class="address">
      {{ office.address }}, {{ office.cityAddress }}, {{ office.stateAddress }},
      {{ office.zipAddress }}
    </div>
    <h3>Office Hours</h3>
    <div class="office-time">
      {{ office.startHour }} to {{ office.endHour }}

      <h3>Phone Number</h3>
      {{ office.phoneNumber }}

      <h3>Co-Pay Amount</h3>
      $ {{ office.price }}
    </div>
  </div>
</template>

<script>
import officeService from "@/services/OfficeService";
export default {
  name: "office-detail",
  data() {
    return {
      office: [],
    };
  },
  created() {
    officeService.getOffice(this.$route.params.officeID).then((respone) => {
      this.office = respone.data;
    });
  },
};
</script>

<style>


.office-info {
  margin: 20px 35%;
  border: 1px solid rgb(180, 180, 180);
  background-color: white;
  justify-items: center;
  width: 80%;
  padding: 0 50px;
  display: grid;
  align-content: center;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);

}
.office_name {
  font-size: 25px;
  font-weight: bold;
  color: plum;
}
.office-info:hover{
    color: rgb(62, 100, 226);
    border: 2px solid rgb(72, 128, 21);

}
.outer {
  display: flex;
  justify-content: center;
  width: 400px;
}
</style>