<template>
  <div id="app">
  <div class="header">  
    <header>
      <img src="../img/logo.png" >
      <h1>TE Health Portal</h1>
    </header>
    <nav>
      <ul class="menu">
      <li v-if="$store.state.token !=''"><router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;&nbsp;</li>
      <!-- <li v-if="$store.state.token !=''" v-show="$store.state.isDoctor===true"><router-link v-bind:to="{name:'update'}" >Availability </router-link></li> -->
        <li v-if="$store.state.token !=''" v-show="$store.state.isDoctor===true"><router-link v-bind:to="{name:'Patient'}" >Patients </router-link></li>  
        <li v-if="$store.state.token != ''" v-show="$store.state.isDoctor===false"><router-link v-bind:to="{name:'Doctor'}" >Doctors </router-link></li>  
        <li v-if="$store.state.token != ''" v-show="$store.state.isDoctor===true" ><router-link v-bind:to="{name:'update'}" >Availability </router-link></li>  
        <li v-if="$store.state.token != ''"><router-link v-bind:to="{name:'officelistview'}" >Offices </router-link></li>
        <li v-if="$store.state.token != ''"><router-link v-bind:to="{name:'appts-view'}" >Appointments </router-link></li>
        <li  v-if="$store.state.token !=''"><router-link v-bind:to="{ name: 'logout' }">Logout</router-link></li>
      
        </ul>     
     </nav>
     </div>
 
    <div class="main">
    <router-view />
    <footer>

    </footer>
    </div>
  </div>
</template>
<script>


 export default {
//   name: "register",
   data() {
     return {
       
       errorMsg: ''
      
    }
   },
    components:{

    }
 }
 
</script>

<style scoped>
body {
    font-family: Arial, Helvetica, sans-serif;
     display: grid;
     height: 100vh;
}
nav ul {
    margin: 0;
    padding: auto;
    display: flex;
    justify-content: flex-end;
    text-align: center;
    justify-content: space-between;   
    display: flex;
    flex : auto;
 
}
nav ul li {
    list-style: none;
    width: 10%;
    background-color: #00afef;
    border-radius: 20px;
    line-height: 30px;
     justify-content: flex-end;
     flex-wrap: wrap;
      
    }
  header img {
    height: 100px;
    width: auto;
}
header h1 {
    padding-left: 10px;
    padding-top: 10px;
    

}
header{
      display: flex;
      margin-top: 0px;
     
     
}
.menu{
  justify-content: space-between;
  justify-content: flex-end;

}
 nav li:hover,
 nav li.router-link-active,
 nav li.router-link-exact-active {
 background: white;

}
#app{
  background:rgb(119, 211, 247);

}
.header{
  background-image: url("../img/main.png");
  padding: 1%;
  background-repeat: no-repeat; 
  width: auto;
  background-size:100%;
}
.footer{
  border: 1px solid Plum;
  background: white;
  width: auto;
  height: auto; 
}
</style>
