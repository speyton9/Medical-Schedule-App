import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import OfficeDetails from '../views/OfficeDetails.vue'
import Doctor from '../views/Doctor.vue'
import Patient from '../views/Patient'
import DoctorDetails from '../views/DoctorDetails.vue'
import PatientAppt from '../views/PatientAppt.vue'
import Appointments from '../views/Appointments'
import Update from '../views/Update.vue'
import OfficeListView from '../views/OfficeListView'
import PatientDetail from '../views/PatientDetail.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    // Home View (what users see when they log-in)
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    // Log-In View 
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    // Log-Out View
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    // Register User View
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    // This link is the general office list
    // Use this router name in navigation bar ONLY
    {
      path:'/offices',
      name:'officelistview',
      component: OfficeListView
    },
    // This link is used when clicking an office card in OfficeList component
    // Takes user to specific Office View
    {
      path: '/offices/:officeID',
      name: 'office',
      component: OfficeDetails
    },
    // This link is the general doctor list
    // Use this router name in navigation bar ONLY
    {
      path: '/doctors',
      name: 'Doctor',
      component: Doctor
    },
    // This link is the general patients list
    // Use this router name in navigation bar ONLY
    {
      path:'/patients',
      name:'Patient',
      component:Patient
      
    },
    {
      path:'/patients/:patientID',
      name:'patientDetail',
      component: PatientDetail
      
    },
    // This link is used when clicking a doctor card in DoctorList component
    // Takes user to specific DoctorDetails View
    {
      path: '/doctors/:doctorID',
      name: 'DoctorDetails',
      component: DoctorDetails
    },
    // This link is used when clicking an appointment card in PatientApptList component
    // Takes user to specific PatientApptDetails View
    {
      path: '/appts/:apptID',
      name: 'PatientAppt',
      component: PatientAppt
    },
    // This link is used when a Doctor or User is looking up a list of their appointments
    // Use this link ONLY in navigation bar
    {
      path: '/appts',
      name: 'appts-view',
      component: Appointments
    },
    // This link is used when a Doctor wants to update their availability
    // Use this link ONLY in navigation bar
    {
      path: '/updateavailability',
      name: 'update',
      component: Update
    }
    // This will be deleted. Reviews will be viewed at specific office details page.
    // {
    //   path: '/reviews/:officeID',
    //   name: 'ReviewAdd',
    //   component: Office
    // },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
