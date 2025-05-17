<template>
  <div class="stat-card card-hover">
    <h3>{{ label }}</h3>
    <p>{{ value }}</p>
  </div>
</template>

<script>
export default {
  name: 'StatCard',
  props: ['label', 'value']
};
</script>

<style scoped>
.stat-card {
  background-color: var(--gray);
  border: 1px solid #ddd;
  padding: 1.25rem;
  border-radius: 10px;
  text-align: center;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.03);
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
}

.stat-card h3 {
  margin-bottom: 0.5rem;
  font-size: 1rem;
  color: #444;
  transition: color 0.3s ease;
}

.stat-card p {
  font-size: 1.6rem;
  font-weight: bold;
  color: var(--black);
  transition: color 0.3s ease;
}
</style>
