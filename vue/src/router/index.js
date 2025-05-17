import { createRouter, createWebHistory } from 'vue-router';
import { useStore } from 'vuex';

// View imports
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import BookingView from '../views/BookingView.vue';
import AdminBookingsView from '../views/AdminBookingsView.vue';
import AdminDashboardView from '../views/AdminDashboardView.vue';
import AdminVehicleView from '../views/AdminVehicleView.vue';
import AdminRidesView from '../views/AdminRidesView.vue';
import ProfileView from '../views/ProfileView.vue';
import AboutView from '../views/AboutView.vue';
import ContactView from '../views/ContactView.vue';
import RatesPolicyView from '../views/RatesPolicyView.vue';

const routes = [
  { path: '/', name: 'home', component: HomeView, meta: { requiresAuth: false } },
  { path: '/login', name: 'login', component: LoginView, meta: { requiresAuth: false } },
  { path: '/logout', name: 'logout', component: LogoutView, meta: { requiresAuth: false } },
  { path: '/book', name: 'book', component: BookingView, meta: { requiresAuth: false } },
  { path: '/about', name: 'about', component: AboutView, meta: { requiresAuth: false } },
  { path: '/contact', name: 'contact', component: ContactView, meta: { requiresAuth: false } },
  { path: '/rates', name: 'RatesPolicyView', component: RatesPolicyView, meta: { requiresAuth: false } },
  { path: '/admin/bookings', name: 'AdminBookingsView', component: AdminBookingsView, meta: { requiresAuth: true } },
  { path: '/admin/dashboard', name: 'AdminDashboardView', component: AdminDashboardView, meta: { requiresAuth: true } },
  { path: '/admin/vehicle', name: 'AdminVehicleView', component: AdminVehicleView, meta: { requiresAuth: true } },
  { path: '/admin/rides', name: 'AdminRidesView', component: AdminRidesView, meta: { requiresAuth: true } },
  { path: '/profile', name: 'profile', component: ProfileView, meta: { requiresAuth: true } }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

// Navigation guard for protected admin routes
router.beforeEach((to) => {
  const store = useStore();
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
  if (requiresAuth && store.state.token === '') {
    return { name: 'login' };
  }
});

export default router;
