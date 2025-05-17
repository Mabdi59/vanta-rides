<template>
  <div class="profile-page">
    <h1>My Profile</h1>

    <div class="profile-card card-hover" v-if="user.username">
      <p><strong>Username:</strong> {{ user.username }}</p>
      <p><strong>Role:</strong> {{ user.role }}</p>
    </div>

    <form @submit.prevent="changePassword" class="password-form">
      <h2>Change Password</h2>

      <div class="form-group">
        <label for="newPassword">New Password</label>
        <input
          type="password"
          id="newPassword"
          v-model="newPassword"
          required
        />
      </div>

      <LoadingSpinner v-if="isLoading" />
      <button type="submit" class="update-btn" :disabled="isLoading">
        {{ isLoading ? 'Updating...' : 'Update Password' }}
      </button>
    </form>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import UserService from '../services/UserService';
import LoadingSpinner from '@/components/LoadingSpinner.vue';

export default {
  name: 'ProfileView',
  components: {
    LoadingSpinner
  },
  data() {
    return {
      newPassword: '',
      isLoading: false
    };
  },
  computed: {
    ...mapState(['user'])
  },
  methods: {
    async changePassword() {
      this.isLoading = true;
      try {
        await UserService.changePassword(this.newPassword);
        this.$emit('toast', 'Password updated successfully.', 'success');
        this.newPassword = '';
      } catch (error) {
        console.error('Password update failed:', error);
        this.$emit('toast', 'Error updating password.', 'error');
      } finally {
        this.isLoading = false;
      }
    }
  }
};
</script>

<style scoped>
.profile-page {
  max-width: 600px;
  margin: auto;
  padding: 2rem;
  transition: all 0.3s ease;
}

.profile-card {
  background: #fdfdfd;
  border: 1px solid #ddd;
  padding: 1.5rem;
  border-radius: 10px;
  margin-bottom: 2rem;
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.password-form h2 {
  margin-bottom: 1rem;
  font-size: 1.3rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  font-weight: bold;
  display: block;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  transition: all 0.25s ease;
}

input:focus {
  outline: none;
  border-color: #c7a008;
  box-shadow: 0 0 6px rgba(199, 160, 8, 0.5);
}

.update-btn {
  width: 100%;
  padding: 1rem;
  background-color: black;
  color: white;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
}

.update-btn:hover:not(:disabled) {
  background-color: #c7a008;
  color: black;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}
</style>
