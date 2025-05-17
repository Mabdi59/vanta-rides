<template>
  <div class="admin-dashboard">
    <h1>Admin Dashboard</h1>

    <!-- Loading -->
    <LoadingSpinner v-if="isLoading" />

    <!-- Dashboard Content -->
    <div v-else>
      <!-- Booking Stats -->
      <section>
        <h2>Booking Stats</h2>
        <div class="stat-grid">
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Total Bookings" :value="counts.total" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Pending" :value="counts.pending" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Confirmed" :value="counts.confirmed" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Completed" :value="counts.completed" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Cancelled" :value="counts.cancelled" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Estimated Revenue" :value="'$' + counts.estimatedEarnings" />
          </div>
        </div>
      </section>

      <!-- Ride Stats -->
      <section>
        <h2>Ride Stats</h2>
        <div class="stat-grid">
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Total Rides" :value="rideCounts.total" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Assigned" :value="rideCounts.assigned" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Completed" :value="rideCounts.completed" />
          </div>
          <div class="stat-card-wrapper card-hover">
            <StatCard label="Cancelled" :value="rideCounts.cancelled" />
          </div>
        </div>
      </section>

      <!-- Quick Links -->
      <section class="quick-links">
        <router-link to="/admin/bookings" class="admin-link">Manage Bookings</router-link>
        <router-link to="/admin/rides" class="admin-link">View Rides</router-link>
        <router-link to="/admin/vehicle" class="admin-link">Vehicle Info</router-link>
        <router-link to="/profile" class="admin-link">My Profile</router-link>
      </section>

      <!-- Today's Rides -->
      <section class="today-section">
        <h2>Today's Rides</h2>
        <ul class="today-list" v-if="todayRides.length">
          <li v-for="ride in todayRides" :key="ride.rideId">
            {{ ride.pickupTime | formatTime }} — {{ ride.pickupLocation }} → {{ ride.dropoffLocation }}
          </li>
        </ul>
        <p v-else>No rides scheduled today.</p>
      </section>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import BookingService from '@/services/BookingService';
import StatCard from '@/components/StatCard.vue';
import LoadingSpinner from '@/components/LoadingSpinner.vue';

export default {
  name: 'AdminDashboardView',
  components: {
    StatCard,
    LoadingSpinner
  },
  data() {
    return {
      counts: {
        total: 0,
        pending: 0,
        confirmed: 0,
        completed: 0,
        cancelled: 0,
        estimatedEarnings: 0
      },
      rideCounts: {
        total: 0,
        assigned: 0,
        completed: 0,
        cancelled: 0
      },
      todayRides: [],
      isLoading: false
    };
  },
  methods: {
    loadStats() {
      this.isLoading = true;

      BookingService.getAllBookings()
        .then(res => {
          const bookings = res.data;
          this.counts.total = bookings.length;
          this.counts.pending = bookings.filter(b => b.status === 'PENDING').length;
          this.counts.confirmed = bookings.filter(b => b.status === 'CONFIRMED').length;
          this.counts.completed = bookings.filter(b => b.status === 'COMPLETED').length;
          this.counts.cancelled = bookings.filter(b => b.status === 'CANCELLED').length;
          this.counts.estimatedEarnings = bookings
            .filter(b => ['CONFIRMED', 'COMPLETED'].includes(b.status)).length * 95;
        })
        .catch(err => {
          console.error('Failed to load booking stats', err);
          this.$emit('toast', 'Failed to load booking stats.', 'error');
        });

      axios.get('/api/rides/enriched')
        .then(res => {
          const rides = res.data;
          this.rideCounts.total = rides.length;
          this.rideCounts.assigned = rides.filter(r => r.status === 'ASSIGNED').length;
          this.rideCounts.completed = rides.filter(r => r.status === 'COMPLETED').length;
          this.rideCounts.cancelled = rides.filter(r => r.status === 'CANCELLED').length;
        })
        .catch(err => {
          console.error('Failed to load ride stats', err);
          this.$emit('toast', 'Failed to load ride stats.', 'error');
        });

      axios.get('/api/rides/today')
        .then(res => {
          this.todayRides = res.data;
        })
        .catch(err => {
          console.error('Failed to load today\'s rides', err);
          this.$emit('toast', 'Failed to load today\'s rides.', 'error');
        })
        .finally(() => {
          this.isLoading = false;
        });
    }
  },
  mounted() {
    this.loadStats();
  },
  filters: {
    formatTime(val) {
      return new Date(val).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    }
  }
};
</script>

<style scoped>
.admin-dashboard {
  max-width: 1000px;
  margin: auto;
  padding: 2rem 1.5rem;
}

h1 {
  font-size: 2.2rem;
  margin-bottom: 2rem;
  text-align: center;
}

h2 {
  font-size: 1.5rem;
  margin-bottom: 1.25rem;
  color: #222;
}

.stat-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(160px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2.5rem;
}

.stat-card-wrapper {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.quick-links {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  margin-bottom: 3rem;
  justify-content: center;
}

.admin-link {
  padding: 0.75rem 1.5rem;
  background-color: black;
  color: white;
  text-decoration: none;
  font-weight: bold;
  border-radius: 5px;
  transition: all 0.3s ease;
}

.admin-link:hover {
  background-color: #c7a008;
  color: black;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.today-section {
  margin-top: 2rem;
}

.today-list {
  padding-left: 1rem;
}

.today-list li {
  margin-bottom: 0.5rem;
  font-size: 1rem;
  color: #333;
}
</style>
