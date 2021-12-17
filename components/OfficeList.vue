<template>
<div class="outer">
  <div class="office-list">
    <h2>Offices</h2>
    <div class="offices">
      <div
        class="office"
        v-for="office in offices"
        v-bind:key="office.id"
        v-on:click="viewOfficeDetails(office.id)"
      >
        <div class="header">
          <h3>{{ office.name }}</h3>
        </div>
        <div class="footer">
          <span class="address">
            {{ office.cityAddress }},
            {{ office.stateAddress }}
            &nbsp; &nbsp; &nbsp; &nbsp;
          </span>
          <span class="hours">
            {{ office.startHour }}
            -
            {{ office.endHour }}
          </span>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import officeService from "../services/OfficeService";

export default {
  name: "office-list",
  data() {
    return {
      offices: [],
    };
  },
  created() {
    officeService.getOffices().then((response) => {
      this.offices = response.data;
    });
  },
  methods: {
    viewOfficeDetails(officeID) {
      this.$router.push({ name: "office", params: { officeID: officeID } });
    },
  },

  
};
</script>

<style >
.outer {
  display: flex;
  justify-content: center;
  width: 400px;
  padding: 20px;
}

.office-list {
  /* padding: 20px 20px;
  margin: 0 auto;*/
  max-width: 600px; 
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
.office {
  /* font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px; */
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;

}
.office:last-child {
  /* border: 0px; */
  margin-bottom: 0px;
}

.office h3 {
  margin-top: 0px;
  font-size: 1.2rem;
}

.office .header {
  display: flex;
  justify-content: space-between;
}

.office .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 1rem;

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