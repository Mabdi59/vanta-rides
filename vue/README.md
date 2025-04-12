# Vanta Rides App (Frontend)

This is the frontend Vue.js application for **Vanta Rides**, a premium black car transportation service. This document walks you through how to set up and run the project. It also explains the core features such as routing, authentication, and state management.

---

## Project Setup

Install dependencies:

```
npm install
```

### Environment File

Update the `.env` file in the root directory to configure your backend API URL:

```
VITE_REMOTE_API=http://localhost:9000
```

> Note: The backend Spring Boot server runs on port `9000`.

Start the development server:

```
npm run dev
```

---

## Authentication System

The project uses a secure token-based login system. Routes are protected by navigation guards.

### Route Protection

Located in `src/router/index.js`:

```js
router.beforeEach((to) => {
  const store = useStore();
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
});
```

The `requiresAuth` key in each route's `meta` field specifies whether login is required.

### Vuex State

Located in `src/store/index.js`, this manages global state for the logged-in user and token:

```js
state: {
  token: currentToken || '',
  user: currentUser || {}
},
```

This data is stored in localStorage for persistence on reloads.

---

## Axios Configuration

Axios requests are automatically configured with the token when present:

```js
axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
```

---

## User Authentication

### Login

`src/views/LoginView.vue` uses `AuthService.js` to call the backend:

```js
login() {
  authService.login(this.user)
    .then(response => {
      this.$store.commit("SET_AUTH_TOKEN", response.data.token);
      this.$store.commit("SET_USER", response.data.user);
      this.$router.push("/");
    });
}
```

### Register

`src/views/RegisterView.vue` handles account creation:

```js
register() {
  authService.register(this.user)
    .then(response => {
      this.$router.push({ path: '/login', query: { registration: 'success' } });
    });
}
```

### Logout

On logout, the token and user are cleared from localStorage and Vuex:

```js
this.$store.commit("LOGOUT");
this.$router.push("/login");
```

---

## Folder Structure

- `/views`: Contains all page-level Vue components
- `/router`: Configures page routing and guards
- `/store`: Vuex store for user and token
- `/services`: Axios calls to backend API

---

This frontend is designed to be paired with the **Vanta Rides** Spring Boot backend.

Ready to launch premium black car services with reliability and style.