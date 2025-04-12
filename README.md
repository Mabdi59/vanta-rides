# Vanta Rides

Vanta Rides is a professional, secure, and modern web platform built for a luxury private transportation business operating with a Cadillac Escalade ESV. This application is designed to streamline customer registration, driver login, and future scheduling and booking capabilities — all starting with a single-vehicle operation and scalable for growth.

## Project Overview

This application was built using:
- **Java + Spring Boot** for backend development
- **Vue 3 + Vuex + Vue Router** for the frontend SPA
- **PostgreSQL** for data storage
- **JWT Authentication** for secure login and token-based user sessions

## Features

✅ User registration and login  
✅ Secure authentication using JWT  
✅ Admin and driver roles  
✅ Structured for scalability and real-world business logic  
✅ Clean, professional UI and source structure

## Technologies

- **Backend:** Java, Spring Boot, PostgreSQL
- **Frontend:** Vue 3, Vuex, Vue Router
- **Security:** JWT (JSON Web Tokens), Bcrypt password encryption
- **Build Tools:** Vite, Maven

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/vanta-rides.git
   cd vanta-rides
   ```

2. Setup the PostgreSQL database:
   ```bash
   cd database
   ./create.sh
   ```

3. Start the backend:
   ```bash
   cd server
   ./mvnw spring-boot:run
   ```

4. Start the frontend:
   ```bash
   cd client
   npm install
   npm run dev
   ```

## Folder Structure

```
vanta-rides/
│
├── client/                # Vue frontend
├── server/                # Java Spring Boot backend
├── database/              # SQL schema, data, and users
├── README.md              # You're here!
```

## License

This project is licensed under the MIT License.

---

Built with ❤️ by Mohamed Abdi using real-world business needs for inspiration.