<template>
  <div class="success-check" v-if="!savingSuccessful">
    <div class="review-bubble" >
      <h2>Leave A Review</h2>
      <form v-on:submit.prevent class="reviewForm">
        <div class="status-message error" v-show="errorMsg !== ''">
          {{ errorMsg }}
        </div>

        <label for="review"></label>
        <textarea
          id="review"
          type="text"
          v-model="review.review"
          autocomplete="off"
          class="form-control"
          placeholder="Please Share Your Feedback on this Office"
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
import reviewService from "../services/ReviewService";
// import officeService from "../services/OfficeService";

export default {
  name: "new-review",

  data() {
    return {
      savingSuccessful: false,
      review: {
        // reviewID: "",
        // officeID: "",
        // userID: "",
        // newReview: "",
        review: "",
      },
      // reviews: [],
      errorMsg: "",
    };
  },
  methods: {
    submitForm() {
      const newReview = {
        // newReview: this.review.newReview,
        review: this.review.review,
        // userID: this.$store.state.user.id,
        officeId: this.$route.params.officeID,
      };
      // add new review
      reviewService
        .addReview(newReview, this.$store.state.user.id, this.$route.params.officeID)
        // .addReview(newReview)
        .then((response) => {
          // const savedReview = response.data;
           this.$router.push({ name: "officelistview" });
          // this.savingSuccessful = true;
          if (response.status === 201) {
            // const savedAppointment = response.data;
            // this.$router.push({ name: "PatientAppt", params: { apptID: savedAppointment.apptId } });
          // this.savingSuccessful = true;
          }
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
          " review. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " review. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " review. Request could not be created.";
      }
    },
  },
  created() {
    // officeService.getOffices().then((response) => {
    //   this.offices = response.data;
    // });
  },
};
</script>


<style >
.success-check {
  display: flex;
  padding: 10px;
  justify-content: center;
}

.review-bubble {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
  width: 100%;
  text-align: center;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);

}
.reviewForm {
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
