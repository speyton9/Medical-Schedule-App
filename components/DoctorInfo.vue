<template>
<div class="doctor-detail">
<div class="doctor-info"> 
  
    <h1>Doctor</h1>
    <span class="doctor_name">Dr. {{ doctor.firstName }} {{ doctor.lastName}}</span>
    <h3>Doctor Availability</h3>
    <div class="hours">
    {{doctor.clockIn}} to {{doctor.clockOut}}
 
      <h3>Email</h3>
      {{doctor.email}}
    </div>
       
</div>
</div>
 
</template>

<script>
import doctorService from "@/services/DoctorService"
export default {
  name:"doctor-info",
   data(){
     return{
       doctor:[]
                 
       };
     },
   created (){
    doctorService.getDoctorInfo(this.$route.params.doctorID).then((respone)=> {
      this.doctor=respone.data;
    })

   }
   

}
</script>

<style scoped>
h1{
  font-size: 1.25rem;
  justify-items: center;
  align-content: center;
  
  

}
h3{
  font-size: 1.25rem;
  justify-items: center;
  align-content: center;

}

.doctor-info{
  /* position: relative;
  left: 15px; */
  /* margin: 20px 35%; */
  border: 1px solid rgb(180, 180, 180);
  background-color: white;
  justify-items: center;
  /* width :20%; */
  padding :20px;
  display: grid;
  align-content: center;
  font-size: 20px;
  color: gray;
  box-shadow: 0 4px 8px 0 rgb(0 0 0 / 50%), 0 6px 20px 0 rgb(0 0 0 / 50%);
  border-radius: 0.25rem;
  padding-top:10px;
  border: 1px;
  /* margin-bottom: 10px; */
  cursor: pointer;

}
  
  .doctor-detail {
  margin: 0 auto;
  max-width: 300px; 
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

 
 .doctor_name{
   font-size: 20px;
   font-weight: bold;
   color: rgb(202, 212, 63);
 }
 .doctor-info:hover{
    color: rgb(62, 100, 226);
    border: 2px solid rgb(72, 128, 21);
}




</style>