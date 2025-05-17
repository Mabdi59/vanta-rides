<template>
  <div class="vehicle-card card-hover">
    <h2>{{ vehicle.make }} {{ vehicle.model }}</h2>
    <ul>
      <li><strong>Year:</strong> {{ vehicle.year }}</li>
      <li><strong>License Plate:</strong> {{ vehicle.licensePlate }}</li>
      <li><strong>Seats:</strong> {{ vehicle.capacity }}</li>
      <li><strong>Color:</strong> {{ vehicle.color }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'VehicleCard',
  props: ['vehicle']
};
</script>

<style scoped>
.vehicle-card {
  background-color: #fff;
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 10px;
  max-width: 500px;
  margin: 1rem auto;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.04);
  transition: all 0.3s ease;
}

/* Hover animation */
.card-hover:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.12);
}

.vehicle-card h2 {
  margin-bottom: 1rem;
  font-size: 1.6rem;
  color: #222;
  text-align: center;
}

.vehicle-card ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.vehicle-card li {
  font-size: 1.05rem;
  margin-bottom: 0.75rem;
  color: #333;
}

.vehicle-card li strong {
  color: #555;
}
</style>
