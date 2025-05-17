<template>
  <footer class="footer shadow-soft fade-in-footer">
    <div class="footer-content container">
      <p>&copy; {{ year }} Vanta Rides. All rights reserved.</p>
      <p class="tagline">Luxury. Safety. Comfort.</p>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
  data() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>

<style scoped>
.footer {
  background-color: var(--black);
  color: var(--white);
  text-align: center;
  padding: 2rem 1rem;
  margin-top: 3rem;
  animation: fade-up 0.4s ease-in;
}

.footer-content p {
  margin: 0.25rem 0;
  font-size: 0.9rem;
  letter-spacing: 0.3px;
}

.tagline {
  font-style: italic;
  color: var(--gold);
}

.shadow-soft {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.04);
}

/* Fade-up animation */
@keyframes fade-up {
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
