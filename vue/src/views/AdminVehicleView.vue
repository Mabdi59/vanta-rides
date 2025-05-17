<template>
  <div class="admin-vehicle">
    <h1>Vehicle Information</h1>

    <!-- Spinner while loading -->
    <LoadingSpinner v-if="isLoading" />

    <!-- Vehicle loaded -->
    <div v-else-if="vehicle" class="vehicle-wrapper card-hover">
      <VehicleCard :vehicle="vehicle" />
    </div>

    <!-- Error fallback -->
    <div v-else class="error-message">
      {{ errorMessage || 'Unable to load vehicle data.' }}
    </div>
  </div>
</template>

<script>
import VehicleService from '@/services/VehicleService';
import VehicleCard from '@/components/VehicleCard.vue';
import LoadingSpinner from '@/components/LoadingSpinner.vue';

export default {
  name: 'AdminVehicleView',
  components: {
    VehicleCard,
    LoadingSpinner
  },
  data() {
    return {
      vehicle: null,
      isLoading: false,
      errorMessage: ''
    };
  },
  mounted() {
    this.isLoading = true;
    VehicleService.getVehicle()
      .then((response) => {
        this.vehicle = response.data;
      })
      .catch((error) => {
        console.error(error);
        this.errorMessage = 'Unable to load vehicle data.';
        this.$emit('toast', this.errorMessage, 'error');
      })
      .finally(() => {
        this.isLoading = false;
      });
  }
};
</script>

<style scoped>
.admin-vehicle {
  max-width: 850px;
  margin: auto;
  padding: 2.5rem 1.5rem;
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 2rem;
  color: #222;
}

.vehicle-wrapper {
  margin-top: 1rem;
  transition: all 0.3s ease;
  border-radius: 10px;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.error-message {
  color: red;
  font-weight: bold;
  text-align: center;
  margin-top: 2rem;
  font-size: 1.05rem;
}
</style>
