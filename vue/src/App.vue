<template>
  <div id="vanta-app">
    <Navbar />
    <main class="main-content container">
      <transition name="fade-page" mode="out-in">
        <router-view @toast="triggerToast" />
      </transition>
    </main>
    <Footer />
    <Toast ref="toast" />
  </div>
</template>

<script>
import './assets/global.css'; 
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
import Toast from './components/Toast.vue';

export default {
  components: { Navbar, Footer, Toast },
  methods: {
    triggerToast(msg, type = 'success') {
      this.$refs.toast.show(msg, type);
    }
  }
};
</script>

<style>
.main-content {
  min-height: calc(100vh - 140px); /* Adjust for Navbar + Footer */
  padding: 2rem 1rem;
}

/* Smooth page transitions */
.fade-page-enter-active {
  animation: fade-in 0.4s ease;
}
@keyframes fade-in {
  0% {
    opacity: 0;
    transform: translateY(20px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
