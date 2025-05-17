BEGIN TRANSACTION;

-- Drop all tables if they exist
DROP TABLE IF EXISTS feedback, payments, rides, ride_requests, vehicles, users CASCADE;

-- USERS table (admin, driver, or future registered users)
CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password_hash VARCHAR(200) NOT NULL,
                       role VARCHAR(50) NOT NULL -- e.g., ADMIN, DRIVER, USER
);

-- VEHICLES (Only one Escalade for now, but scalable)
CREATE TABLE vehicles (
                          vehicle_id SERIAL PRIMARY KEY,
                          make VARCHAR(50) NOT NULL,
                          model VARCHAR(50) NOT NULL,
                          year INT NOT NULL,
                          license_plate VARCHAR(20) NOT NULL,
                          color VARCHAR(20),
                          capacity INT
);

-- RIDE REQUESTS (Submitted by public or optionally linked to users)
CREATE TABLE ride_requests (
                               request_id SERIAL PRIMARY KEY,
                               pickup_location VARCHAR(255) NOT NULL,
                               dropoff_location VARCHAR(255) NOT NULL,
                               pickup_time TIMESTAMP NOT NULL,
                               notes TEXT,
                               status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, CONFIRMED, COMPLETED, CANCELLED
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               vehicle_id INT REFERENCES vehicles(vehicle_id) ON DELETE SET NULL, -- optional link to vehicle
                               user_id INT REFERENCES users(user_id) ON DELETE SET NULL           -- optional if user account exists
);

-- RIDES (Created after a booking is confirmed and assigned)
CREATE TABLE rides (
                       ride_id SERIAL PRIMARY KEY,
                       request_id INT REFERENCES ride_requests(request_id) ON DELETE CASCADE,
                       vehicle_id INT REFERENCES vehicles(vehicle_id) ON DELETE SET NULL,
                       driver_id INT REFERENCES users(user_id) ON DELETE SET NULL, -- optional, links to assigned driver
                       status VARCHAR(20) NOT NULL DEFAULT 'CONFIRMED',
                       assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PAYMENTS (optional future feature)
CREATE TABLE payments (
                          payment_id SERIAL PRIMARY KEY,
                          ride_id INT REFERENCES rides(ride_id) ON DELETE CASCADE,
                          amount NUMERIC(10,2),
                          status VARCHAR(20), -- PAID, FAILED, REFUNDED
                          method VARCHAR(20), -- CARD, CASH, APPLE_PAY, etc.
                          paid_at TIMESTAMP
);

-- FEEDBACK (optional but useful)
CREATE TABLE feedback (
                          feedback_id SERIAL PRIMARY KEY,
                          ride_id INT REFERENCES rides(ride_id) ON DELETE CASCADE,
                          rating INT CHECK (rating >= 1 AND rating <= 5),
                          comment TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contact_messages (
                                  message_id SERIAL PRIMARY KEY,
                                  name VARCHAR(100) NOT NULL,
                                  email VARCHAR(150) NOT NULL,
                                  message TEXT NOT NULL,
                                  submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


COMMIT TRANSACTION;
