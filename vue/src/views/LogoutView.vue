<template>
  <div class="logout-page">
    <div class="logout-message card-hover">
      <h1>Signing you out...</h1>
      <p>You are being redirected to the login screen.</p>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    // Brief delay to display logout message clearly before redirect
    setTimeout(() => {
      this.$router.push("/login");
    }, 1500);
  }
};
</script>

<style scoped>
.logout-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
  padding: 2rem;
  animation: fadeIn 0.5s ease;
}

.logout-message {
  text-align: center;
  background-color: #fff;
  padding: 2rem 3rem;
  border-radius: 12px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.logout-message h1 {
  margin-bottom: 1rem;
  color: #222;
  font-size: 1.8rem;
}

.logout-message p {
  font-size: 1.05rem;
  color: #555;
}

/* Animation */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
