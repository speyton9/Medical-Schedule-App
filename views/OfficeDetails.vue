<template>
  <div class= "details-vue-space">
  <div>
    <office-detail />
    <update-office v-show="$store.state.isDoctor===true" />
    <review-list-new-2 />
    <new-review v-show="$store.state.isDoctor===false" />
  </div>
  </div>
</template>

<script>
import NewReview from "../components/NewReview.vue";
import OfficeDetail from "../components/OfficeDetail.vue";
import ReviewListNew2 from "../components/ReviewListNew2.vue";
import UpdateOffice from "../components/UpdateOffice.vue";

export default {
  name: "office",
  components: {
    ReviewListNew2,
    OfficeDetail,
    NewReview,
    UpdateOffice,
  },
};
</script>

<style scoped>
.details-vue-space{
  margin-top: 20px;
}

div{
  
  margin: 0 auto;
  max-width: 600px; 
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* text-align: center; */
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 20px 20px 20px 20px;
  text-align: center;
  /* align-items: center; */
  flex-grow:.5;
}
</style>
