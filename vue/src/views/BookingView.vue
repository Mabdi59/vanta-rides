<template>
  <div class="booking-page card-hover">
    <h1>Book Your Ride</h1>
    <p class="subtitle">Please provide your ride details below.</p>

    <form @submit.prevent="submitBooking" class="booking-form">
      <div class="form-group">
        <label for="pickupLocation">Pickup Location</label>
        <input type="text" id="pickupLocation" v-model="form.pickupLocation" required />
      </div>

      <div class="form-group">
        <label for="dropoffLocation">Dropoff Location</label>
        <input type="text" id="dropoffLocation" v-model="form.dropoffLocation" required />
      </div>

      <div class="form-group">
        <label for="pickupTime">Pickup Time</label>
        <input type="datetime-local" id="pickupTime" v-model="form.pickupTime" required />
      </div>

      <div class="form-group">
        <label for="vehicleId">Select Vehicle</label>
        <select id="vehicleId" v-model="form.vehicleId" required>
          <option v-for="vehicle in vehicles" :key="vehicle.vehicleId" :value="vehicle.vehicleId">
            {{ vehicle.make }} {{ vehicle.model }} ({{ vehicle.color }})
          </option>
        </select>
      </div>

      <div class="form-group">
        <label for="notes">Additional Notes (optional)</label>
        <textarea id="notes" v-model="form.notes" rows="3" />
      </div>

      <LoadingSpinner v-if="isLoading" />

      <button type="submit" class="submit-btn" :disabled="isLoading">
        {{ isLoading ? 'Processing...' : 'Request Ride' }}
      </button>
    </form>
  </div>
</template>

<script>
import BookingService from '@/services/BookingService';
import VehicleService from '@/services/VehicleService';
import { mapState } from 'vuex';
import LoadingSpinner from '@/components/LoadingSpinner.vue';

export default {
  name: 'BookingView',
  components: { LoadingSpinner },
  data() {
    return {
      form: {
        pickupLocation: '',
        dropoffLocation: '',
        pickupTime: '',
        notes: '',
        vehicleId: null,
        userId: null
      },
      vehicles: [],
      isLoading: false
    };
  },
  computed: {
    ...mapState(['userId'])
  },
  methods: {
    loadVehicles() {
      VehicleService.getAllVehicles()
        .then(res => {
          this.vehicles = res.data;
          if (this.vehicles.length > 0) {
            this.form.vehicleId = this.vehicles[0].vehicleId;
          }
        })
        .catch(err => {
          console.error('Could not load vehicles', err);
          this.$emit('toast', 'Failed to load available vehicles.', 'error');
        });
    },
    submitBooking() {
      const now = new Date();
      const pickup = new Date(this.form.pickupTime);

      if (!this.form.pickupLocation || !this.form.dropoffLocation) {
        this.$emit('toast', 'Pickup and dropoff locations are required.', 'error');
        return;
      }

      if (!this.form.pickupTime || isNaN(pickup)) {
        this.$emit('toast', 'Pickup time is required.', 'error');
        return;
      }

      if (pickup <= now) {
        this.$emit('toast', 'Pickup time must be in the future.', 'error');
        return;
      }

      if (this.form.notes && this.form.notes.length > 500) {
        this.$emit('toast', 'Notes must be under 500 characters.', 'error');
        return;
      }

      if (!this.form.vehicleId) {
        this.$emit('toast', 'Please select a vehicle.', 'error');
        return;
      }

      if (this.userId) {
        this.form.userId = this.userId;
      }

      this.isLoading = true;

      BookingService.createBooking(this.form)
        .then(() => {
          this.resetForm();
          this.$emit('toast', 'Your ride request has been submitted!', 'success');
        })
        .catch(err => {
          console.error(err);
          this.$emit('toast', 'An error occurred while submitting. Please try again.', 'error');
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    resetForm() {
      this.form = {
        pickupLocation: '',
        dropoffLocation: '',
        pickupTime: '',
        notes: '',
        vehicleId: this.vehicles.length > 0 ? this.vehicles[0].vehicleId : null,
        userId: this.userId || null
      };
    }
  },
  mounted() {
    this.loadVehicles();
  }
};
</script>

<style scoped>
.booking-page {
  max-width: 650px;
  margin: auto;
  background-color: #fff;
  padding: 2.5rem 2rem;
  border-radius: 12px;
  box-shadow: 0 0 18px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 0.5rem;
}

.subtitle {
  text-align: center;
  margin-bottom: 2rem;
  color: #666;
  font-size: 1rem;
}

.booking-form .form-group {
  margin-bottom: 1.5rem;
}

.booking-form label {
  display: block;
  font-weight: bold;
  margin-bottom: 0.5rem;
  font-size: 0.95rem;
}

.booking-form input,
.booking-form textarea,
.booking-form select {
  width: 100%;
  padding: 0.75rem;
  border-radius: 6px;
  border: 1px solid #ccc;
  transition: border 0.25s ease, box-shadow 0.25s ease;
  font-size: 1rem;
  background-color: #fafafa;
}

.booking-form input:focus,
.booking-form textarea:focus,
.booking-form select:focus {
  outline: none;
  border-color: #c7a008;
  box-shadow: 0 0 6px rgba(199, 160, 8, 0.4);
  background-color: #fff;
}

.submit-btn {
  width: 100%;
  padding: 1rem;
  background-color: #c7a008;
  color: black;
  font-weight: bold;
  border: none;
  border-radius: 6px;
  font-size: 1rem;
  cursor: pointer;
  transition: all 0.3s ease;
}

.submit-btn:hover:not(:disabled) {
  background-color: #e0b70b;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

/* Card hover effect */
.card-hover {
  transition: all 0.3s ease;
}
.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
}
</style>
