<template>
  <nav :class="['navbar', { scrolled: isScrolled }]">
    <div class="brand">
      <router-link to="/" class="logo">
        <img src="@/assets/logo-img.png" alt="Vanta Logo" class="logo-img" />
        <span class="logo-text">Vanta Rides</span>
      </router-link>
    </div>

    <ul class="nav-links">
      <li><router-link to="/">Home</router-link></li>
      <li><router-link to="/book">Book a Ride</router-link></li>
      <li><router-link to="/about">About</router-link></li>
      <li><router-link to="/contact">Contact</router-link></li>
      <li><router-link to="/rates">Rates & Policies</router-link></li>
      <li v-if="isLoggedIn"><router-link to="/admin/dashboard">Dashboard</router-link></li>
      <li v-if="isLoggedIn"><router-link to="/admin/bookings">Bookings</router-link></li>
      <li v-if="isLoggedIn"><router-link to="/admin/rides">Rides</router-link></li>
      <li v-if="isLoggedIn"><router-link to="/profile">Profile</router-link></li>
      <li v-if="isLoggedIn"><router-link to="/logout">Logout</router-link></li>
      <li v-else><router-link to="/login">Admin Login</router-link></li>
    </ul>
  </nav>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'Navbar',
  data() {
    return {
      isScrolled: false
    };
  },
  computed: {
    ...mapState(['token']),
    isLoggedIn() {
      return this.token !== '';
    }
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll);
  },
  methods: {
    handleScroll() {
      this.isScrolled = window.scrollY > 10;
    }
  }
};
</script>

<style scoped>
.navbar {
  position: sticky;
  top: 0;
  z-index: 100;
  background-color: var(--black);
  color: var(--white);
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  transition: box-shadow 0.3s ease, background-color 0.3s ease;
  animation: fade-down 0.4s ease-in;
}

.scrolled {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.25);
}

.brand .logo {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: var(--white);
  font-weight: bold;
  transition: color 0.3s ease;
}

.brand .logo:hover {
  color: var(--gold);
}

.logo-img {
  height: 28px;
  width: auto;
  margin-right: 0.5rem;
  object-fit: contain;
  vertical-align: middle;
  transition: transform 0.3s ease;
}

.logo-text {
  font-size: 1.5rem;
}

.nav-links {
  list-style: none;
  display: flex;
  flex-wrap: wrap;
  gap: 1.5rem;
  margin: 0;
  padding: 0;
}

.nav-links a {
  color: var(--white);
  text-decoration: none;
  transition: color 0.3s, transform 0.3s;
}

.nav-links a:hover {
  color: var(--gold);
  transform: translateY(-1px);
}

@keyframes fade-down {
  0% {
    opacity: 0;
    transform: translateY(-20px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
