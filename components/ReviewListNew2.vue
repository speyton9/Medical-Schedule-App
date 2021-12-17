<template>
  <div class="outer-outer">
    <div class="UpdateOffice-Outer">
      <h2>Office Reviews</h2>
      <div class="updateOfficeForm">
        <div class="review-list">
          <div class="review" v-for="(review, index) in reviews" :key="index"
             v-on:click="reroute(review.patientId)" >
            <!-- changed stuff in lines 8 (used to be review.id) & 9 -->
            <div class="header">
              <h3>{{ review.review }}</h3>
              <!-- <h2>{{ review.name }}</h2> -->
            </div>
            <div class="footer">
              <span class="name"> -{{ review.name }} </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import reviewService from "../services/ReviewService";

export default {
  name: "review-list",
  data() {
    return {
      reviews: []
    };
  },
  created() {
    reviewService.getReviews(this.$route.params.officeID).then((response) => {
      this.reviews = response.data;
    });
  },
  methods: {
    //Reroutes to patient info onClick
    reroute(patientId) {
      if (this.$store.state.isDoctor == true) {
        this.$router.push({
          name: "patientDetail",
          params: { patientID: patientId },
        });
      }
    },
  },
};
</script>

<style scoped>

.list-outer {
  display: flex;
  justify-content: center;
  flex-grow: 1;
}

.outer {
  display: flex;
  justify-content: center;
  width: 400px;
}

.outer-outer {
  display: flex;
  /* padding: 5px; */
  justify-content: center;
  margin: 20px 0;
}

.UpdateOffice-Outer {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  width: 95%;
  height: 100%;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);
}
.updateOfficeForm {
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}

.btn {
  margin-left: 180px;
}

.office-list {
  /* padding: 20px 20px;
  margin: 0 auto;*/
  max-width: 600px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  /* text-align: center; */
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  text-align: center;
  /* align-items: center; */
  flex-grow: 1;
}

.review-list-outer {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.review-list {
  /* padding: 20px 20px;
  margin: 0 auto;*/
  max-width: 600px; 
  max-height: 200px;
  /* width: 100%; */
  /* display: flex; */
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
  grid-row: column;
  /* flex-direction: column; */
  grid-template-columns: repeat(3,auto) ;
  justify-content: space-around;
  /* text-align: center; */
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 20px 20px 20px 20px;
  text-align: center;
  /* align-items: center; */
  /* flex-grow: 1; */
  overflow-y: scroll;
  /* flex-wrap: wrap; */
  width: auto;
  
  justify-content: center;
}

.reviews {
  display: flex;
  width: 100%;
  flex-grow: 1;
}

.review {
  /* font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px; */
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  /* margin-bottom: 10px; */
  margin: 10px;
  cursor: pointer;
  /* flex-basis: 250px; */
  width: auto;
  height: 150px;
  /* align-self: center; */
  flex-wrap: wrap;
  overflow: hidden;


   
}
.review:last-child {
  /* border: 0px; */
  margin-bottom: 0px;
}

.review h3 {
  margin-top: 0px;
  font-size: .75rem;
}
.header {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  /* inline-size: 150px;
  overflow-wrap: break-word; */
  overflow-wrap: break-word;
    
}

.review .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
 
}
</style>