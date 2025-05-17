<template>
  <div class="admin-rides">
    <h1>Assigned Rides</h1>

    <LoadingSpinner v-if="isLoading" />

    <div v-else-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>

    <div v-else-if="rides.length === 0" class="empty-message">
      No rides assigned yet.
    </div>

    <div v-else class="rides-list">
      <div v-for="ride in rides" :key="ride.rideId" class="ride-card card-hover">
        <p><strong>Ride ID:</strong> {{ ride.rideId }}</p>
        <p><strong>Pickup Location:</strong> {{ ride.pickupLocation }}</p>
        <p><strong>Dropoff Location:</strong> {{ ride.dropoffLocation }}</p>
        <p><strong>Pickup Time:</strong> {{ formatDate(ride.pickupTime) }}</p>
        <p v-if="ride.notes"><strong>Notes:</strong> {{ ride.notes }}</p>
        <p><strong>Status:</strong> {{ ride.status }}</p>
        <p><strong>Assigned At:</strong> {{ formatDate(ride.assignedAt) }}</p>
        <p v-if="ride.driverId"><strong>Driver ID:</strong> {{ ride.driverId }}</p>

        <!-- Driver Assignment -->
        <div v-if="ride.status === 'ASSIGNED'" class="driver-assign">
          <label>Select Driver:</label>
          <select v-model="ride.selectedDriverId">
            <option disabled value="">-- Select Driver --</option>
            <option v-for="driver in drivers" :key="driver.driverId" :value="driver.driverId">
              {{ driver.name }}
            </option>
          </select>
          <button
            @click="assignDriver(ride.rideId, ride.selectedDriverId)"
            :disabled="assigningId === ride.rideId"
          >
            {{ assigningId === ride.rideId ? 'Assigning...' : 'Assign Driver' }}
          </button>
        </div>

        <!-- Mark Completed -->
        <div v-if="ride.status === 'ASSIGNED'" class="ride-actions">
          <button @click="updateRideStatus(ride.rideId, 'COMPLETED')" class="complete-btn">
            Mark Completed
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import LoadingSpinner from '@/components/LoadingSpinner.vue';

export default {
  name: 'AdminRidesView',
  components: {
    LoadingSpinner
  },
  data() {
    return {
      rides: [],
      drivers: [],
      isLoading: false,
      errorMessage: '',
      assigningId: null
    };
  },
  methods: {
    loadRides() {
      this.isLoading = true;
      axios
        .get('/api/rides/enriched')
        .then((response) => {
          this.rides = response.data.map(r => ({
            ...r,
            selectedDriverId: r.driverId || ''
          }));
        })
        .catch((error) => {
          console.error(error);
          this.errorMessage = 'Unable to load assigned rides.';
          this.$emit('toast', this.errorMessage, 'error');
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    loadDrivers() {
      axios.get('/api/drivers')
        .then(res => {
          this.drivers = res.data;
        })
        .catch(err => {
          console.error('Failed to load drivers:', err);
          this.$emit('toast', 'Failed to load drivers.', 'error');
        });
    },
    assignDriver(rideId, driverId) {
      if (!driverId) {
        this.$emit('toast', 'Please select a driver before assigning.', 'error');
        return;
      }

      this.assigningId = rideId;
      axios.put(`/api/rides/${rideId}/driver`, { driverId })
        .then(() => {
          this.$emit('toast', 'Driver assigned successfully.', 'success');
          this.loadRides();
        })
        .catch((err) => {
          console.error(err);
          this.$emit('toast', 'Error assigning driver.', 'error');
        })
        .finally(() => {
          this.assigningId = null;
        });
    },
    updateRideStatus(rideId, newStatus) {
      axios.put(`/api/rides/${rideId}/status`, { status: newStatus })
        .then(() => {
          this.$emit('toast', `Ride marked as ${newStatus.toLowerCase()}.`, 'success');
          this.loadRides();
        })
        .catch(() => {
          this.$emit('toast', 'Failed to update ride status.', 'error');
        });
    },
    formatDate(datetime) {
      const d = new Date(datetime);
      return d.toLocaleString();
    }
  },
  mounted() {
    this.loadRides();
    this.loadDrivers();
  }
};
</script>

<style scoped>
.admin-rides {
  max-width: 900px;
  margin: auto;
  padding: 2.5rem 1.5rem;
}

h1 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
  text-align: center;
}

.ride-card {
  background: #fff;
  border: 1px solid #ddd;
  padding: 1.5rem;
  border-radius: 12px;
  margin-bottom: 1.75rem;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.04);
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
}

p {
  font-size: 1rem;
  margin: 0.35rem 0;
  color: #333;
}

.driver-assign {
  margin-top: 1rem;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 0.75rem;
}

.driver-assign label {
  font-weight: bold;
  margin-right: 0.5rem;
}

.driver-assign select {
  padding: 0.5rem;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.driver-assign button {
  padding: 0.5rem 1.2rem;
  background-color: #2ecc71;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.driver-assign button:hover:not(:disabled) {
  background-color: #27ae60;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.ride-actions {
  margin-top: 1rem;
}

.complete-btn {
  background-color: #3498db;
  color: white;
  padding: 0.5rem 1.4rem;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
}

.complete-btn:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.error-message {
  color: red;
  text-align: center;
  margin-top: 1rem;
  font-weight: bold;
  font-size: 1.05rem;
}

.empty-message {
  text-align: center;
  font-style: italic;
  color: #666;
  margin-top: 2rem;
  font-size: 1.1rem;
}
</style>
