<template>
  <div id="login">
    <form @submit.prevent="login" class="login-form card-hover">
      <h1>Please Sign In</h1>

      <div role="alert" v-if="$route.query.registration" class="alert">
        Thank you for registering, please sign in.
      </div>

      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>

      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>

      <LoadingSpinner v-if="isLoading" />

      <button type="submit" :disabled="isLoading">
        {{ isLoading ? 'Signing in...' : 'Sign in' }}
      </button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import LoadingSpinner from "@/components/LoadingSpinner.vue";

export default {
  name: "LoginView",
  components: {
    LoadingSpinner
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      isLoading: false
    };
  },
  methods: {
    login() {
      this.isLoading = true;
      authService
        .login(this.user)
        .then(response => {
          if (response.status === 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$emit("toast", "Login successful. Welcome!", "success");
            this.$router.push("/");
          }
        })
        .catch(error => {
          if (error.response && error.response.status === 401) {
            this.$emit("toast", "Invalid username or password.", "error");
          } else {
            console.error("Login failed:", error);
            this.$emit("toast", "An error occurred. Please try again later.", "error");
          }
        })
        .finally(() => {
          this.isLoading = false;
        });
    }
  }
};
</script>

<style scoped>
#login {
  min-height: 90vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  animation: fadeIn 0.6s ease;
}

.login-form {
  width: 100%;
  max-width: 420px;
  background-color: #fff;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  margin-bottom: 1.5rem;
  font-size: 1.8rem;
}

.alert {
  background-color: #f9f9f9;
  border-left: 5px solid #c7a008;
  padding: 1rem;
  margin-bottom: 1.5rem;
  font-size: 0.95rem;
  color: #444;
  border-radius: 5px;
}

.form-input-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.4rem;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 0.75rem;
  border-radius: 5px;
  border: 1px solid #ccc;
  transition: all 0.25s ease;
}

input:focus {
  outline: none;
  border-color: #c7a008;
  box-shadow: 0 0 6px rgba(199, 160, 8, 0.5);
}

button {
  width: 100%;
  padding: 1rem;
  background-color: #c7a008;
  color: black;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

button:hover:not(:disabled) {
  background-color: #e0b70b;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

/* Animation */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
