<template>
  <div id="login" class="text-center">
      <form class="form-signin" @submit.prevent="login">
      <div class="box">
        <h1>Please Sign In</h1>
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
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
          <button type="submit" class="btn">Sign in</button>
          <router-link :to="{ name: 'register' }" tag="button" class="btn2">Sign Up</router-link>
      </div>
    </form>
    <!-- <div class="link">
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
    </div> -->
    <footer>
      
    </footer>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            const role=response.data.user.authorities[0].name;
              if(role==="ROLE_DOCTOR"){
                this.$router.push({name:"home"})
                this.$store.state.isDoctor=true;
              }else if (role==="ROLE_PATIENT"){
                this.$router.push({name:"home"})
                this.$store.state.isDoctor=false;
              }else{
                this.$router.push("/");
                this.$store.state.isDoctor=false;

              }
            
          }
    
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>
body {
  background-image: url("/img/main.png");
  font-family: "Open Sans", sans-serif;
  background: #3498db;
  margin: 0 auto 0 auto;
  width: 100%;
  text-align: center;
  margin: 20px 0px 20px 0px;
}

h1 {
  font-size: 1.5em;
  color: #525252;
}


.box {
  
  background: white;
  width: 350px;
  height: 100%;
  border-radius: 6px;
  margin: 70px auto 0 auto;
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
  width: 300px;
  font-size: 1em;
  color: black;
}

.btn {
  background: #2ecc71;
  width: 125px;
  padding-top: 5px;
  padding-bottom: 5px;
  color: white;
  border-radius: 4px;
  border: #27ae60 1px solid;
  margin-top: 20px;
  margin-bottom: 20px;
  float: left;
  margin-left: 45px;
  font-weight: 800;
  font-size: 0.8em;
}

.btn:hover {
  background: #2cc06b;
}

.btn2{
  float:left;
  background:#3498db;
  width:125px;  padding-top:5px;
  padding-bottom:5px;
  color:white;
  border-radius:4px;
  border: #2980b9 1px solid;
  margin-top:20px;
  margin-bottom:20px;
  margin-left:10px;
  font-weight:800;
  font-size:0.8em;
}

.btn2:hover{ 
background:#3594D2; 
}



 

</style>
