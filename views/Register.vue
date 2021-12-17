<template>
  <div id="register" class="text-center">
    <div class="box">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <label for="username" class="sr-only"></label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only"></label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
        <label for="first_name" class="sr-only"></label>
        <input
          type="text"
          id="first_name"
          class="form-control"
          placeholder="First Name"
          v-model="user.firstName"
          required
          autofocus
        />
        <label for="last_name" class="sr-only"></label>
        <input
          type="text"
          id="last_name"
          class="form-control"
          placeholder="Last Name"
          v-model="user.lastName"
          required
          autofocus
        />
        <label for="email" class="sr-only"></label>
        <input
          type="text"
          id="email"
          class="form-control"
          placeholder="Email"
          v-model="user.email"
          required
          autofocus
        />
        <div class="label">
          <label for="role" class="sr-only">Choose Account Type:</label>
        </div>
        <select
          name="role"
          id="role"
          class="form-control2"
          v-model="user.role"
          required
          autofocus
        >
          <option value="patient">Patient</option>
          <option value="doctor">Doctor</option>
        </select>
        <!-- <router-link :to="{ name: 'login' }">Have an account?</router-link> -->
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
        <router-link :to="{ name: 'login' }" tag="button" class="btn2"
          >Have an Account?</router-link
        >
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        first_name: "",
        last_name: "",
        email: "",
        role: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              authService
                .login(this.user)
                .then((response) => {
                  if (response.status == 200) {
                    this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                    this.$store.commit("SET_USER", response.data.user);
                    const role = response.data.user.authorities[0].name;
                      if (role === "ROLE_DOCTOR") {
                        this.$router.push({ name: "home" });
                        this.$store.state.isDoctor = true;
                      } else if (role === "ROLE_PATIENT") {
                        this.$router.push({ name: "home" });
                        this.$store.state.isDoctor = false;
                      } else {
                        this.$router.push("/");
                        this.$store.state.isDoctor = false;
                      }
                    // this.$router.push("/");
                  }
                })
                .catch((error) => {
                  const response = error.response;

                  if (response.status === 401) {
                    this.invalidCredentials = true;
                  }
                });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
body {
  font-family: "Open Sans", sans-serif;
  background: rgb(119, 211, 247);
  margin: 0 auto 0 auto;
  width: 100%;
  text-align: center;
}

h1 {
  font-size: 1.5em;
  color: #525252;
}

.box {
  background: white;
  width: 350px;
  border-radius: 6px;
  margin: 0 auto 0 auto;
  padding: 0px 0px 70px 0px;
  border: #2980b9 4px solid;
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

.form-control2 {
  border-radius: 4px;
  background: #ecf0f1;
  border: #ccc 1px solid;
  padding: 8px;
  width: 250px;
  font-size: 1em;
  color: black;
}

.label {
  font-size: 16px;
  padding: 8px;
}

.btn {
  background: #2ecc71;
  width: 150px;
  padding-top: 5px;
  padding-bottom: 5px;
  color: white;
  border-radius: 4px;
  border: #27ae60 1px solid;
  margin-top: 20px;
  margin-bottom: 20px;
  float: left;
  margin-left: 16px;
  font-weight: 800;
  font-size: 0.8em;
}

.btn:hover {
  background: #2cc06b;
}

.btn2 {
  float: left;
  background: #3498db;
  width: 150px;
  padding-top: 5px;
  padding-bottom: 5px;
  color: white;
  border-radius: 4px;
  border: #2980b9 1px solid;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 18px;
  font-weight: 800;
  font-size: 0.8em;
}

.btn2:hover {
  background: #3594d2;
}
</style>
