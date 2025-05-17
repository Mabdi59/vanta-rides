<template>
  <div class="spinner-wrapper">
    <div class="loader" aria-label="Loading..."></div>
  </div>
</template>

<style scoped>
.spinner-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 2rem 0;
  min-height: 60px;
}

.loader {
  width: 36px;
  height: 36px;
  border: 4px solid var(--gray);
  border-top: 4px solid var(--gold);
  border-radius: 50%;
  animation: spin 1s linear infinite;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.08);
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
