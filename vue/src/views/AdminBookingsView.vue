<template>
  <div class="admin-bookings">
    <h1>All Bookings</h1>

    <!-- Loading Spinner -->
    <LoadingSpinner v-if="isLoading" />

    <!-- No Bookings Message -->
    <div v-else-if="bookings.length === 0" class="empty-message">
      No bookings found.
    </div>

    <!-- Booking Cards -->
    <div v-else class="bookings-list">
      <div
        v-for="booking in bookings"
        :key="booking.requestId"
        class="booking-card card-hover"
      >
        <h3>{{ formatDateTime(booking.pickupTime) }}</h3>
        <p><strong>Request ID:</strong> {{ booking.requestId }}</p>
        <p><strong>Pickup:</strong> {{ booking.pickupLocation }}</p>
        <p><strong>Dropoff:</strong> {{ booking.dropoffLocation }}</p>
        <p>
          <strong>Status:</strong>
          <span :class="'status ' + booking.status.toLowerCase()">
            {{ booking.status }}
          </span>
        </p>
        <p v-if="booking.notes"><strong>Notes:</strong> {{ booking.notes }}</p>
        <p v-if="booking.vehicleId"><strong>Vehicle ID:</strong> {{ booking.vehicleId }}</p>

        <!-- Action Spinner -->
        <div v-if="actionLoadingId === booking.requestId">
          <LoadingSpinner />
        </div>

        <!-- Action Buttons -->
        <div v-else>
          <div class="actions" v-if="booking.status === 'PENDING'">
            <button
              @click="updateStatus(booking.requestId, 'CONFIRMED')"
              class="confirm-btn"
            >
              Confirm
            </button>
            <button
              @click="updateStatus(booking.requestId, 'CANCELLED')"
              class="cancel-btn"
            >
              Cancel
            </button>
          </div>
          <div class="actions" v-else-if="booking.status === 'CONFIRMED'">
            <button
              @click="updateStatus(booking.requestId, 'COMPLETED')"
              class="complete-btn"
            >
              Mark Completed
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BookingService from '@/services/BookingService';
import LoadingSpinner from '@/components/LoadingSpinner.vue';

export default {
  name: 'AdminBookingsView',
  components: { LoadingSpinner },
  data() {
    return {
      bookings: [],
      isLoading: false,
      actionLoadingId: null
    };
  },
  methods: {
    loadBookings() {
      this.isLoading = true;
      BookingService.getAllBookings()
        .then(res => {
          this.bookings = res.data;
        })
        .catch(err => {
          console.error(err);
          this.$emit('toast', 'Error fetching bookings.', 'error');
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    updateStatus(id, newStatus) {
      this.actionLoadingId = id;
      BookingService.updateBookingStatus(id, newStatus)
        .then(() => {
          this.$emit('toast', `Booking marked as ${newStatus.toLowerCase()}.`, 'success');
          this.loadBookings();
        })
        .catch(err => {
          console.error(err);
          this.$emit('toast', 'Error updating status.', 'error');
        })
        .finally(() => {
          this.actionLoadingId = null;
        });
    },
    formatDateTime(dt) {
      const d = new Date(dt);
      return d.toLocaleString();
    }
  },
  mounted() {
    this.loadBookings();
  }
};
</script>

<style scoped>
.admin-bookings {
  max-width: 960px;
  margin: auto;
  padding: 2.5rem 1.5rem;
}

h1 {
  font-size: 2rem;
  margin-bottom: 2rem;
  text-align: center;
}

.booking-card {
  background: #ffffff;
  border: 1px solid #ddd;
  padding: 1.5rem;
  border-radius: 12px;
  margin-bottom: 1.75rem;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.04);
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
}

h3 {
  font-size: 1.15rem;
  margin-bottom: 0.75rem;
  color: #111;
}

p {
  font-size: 1rem;
  margin: 0.4rem 0;
  color: #333;
}

.status {
  font-weight: bold;
  text-transform: uppercase;
}
.status.pending {
  color: #f39c12;
}
.status.confirmed {
  color: #27ae60;
}
.status.completed {
  color: #2980b9;
}
.status.cancelled {
  color: #e74c3c;
}

.actions {
  margin-top: 1rem;
  display: flex;
  gap: 0.75rem;
  flex-wrap: wrap;
}

button {
  padding: 0.6rem 1.4rem;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  cursor: pointer;
  font-size: 0.95rem;
  transition: all 0.3s ease;
}

button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.confirm-btn {
  background-color: #27ae60;
  color: white;
}

.cancel-btn {
  background-color: #c0392b;
  color: white;
}

.complete-btn {
  background-color: #3498db;
  color: white;
}

.empty-message {
  text-align: center;
  font-style: italic;
  color: #777;
  margin-top: 2rem;
  font-size: 1.1rem;
}
</style>
