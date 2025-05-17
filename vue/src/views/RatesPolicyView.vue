<template>
  <div class="rates-page card-hover">
    <h1>Rates & Policies</h1>
    <p class="subtitle">Transparent pricing. Clear expectations. Luxury without compromise.</p>

    <div class="card-section">
      <!-- Standard Fare -->
      <div class="rate-card">
        <h2>💼 Standard Fare</h2>
        <ul>
          <li><strong>Base Rate:</strong> $85 minimum (includes first 10 miles or 30 minutes)</li>
          <li><strong>Hourly Rate:</strong> $95/hour (2-hour minimum)</li>
          <li><strong>Additional Miles:</strong> $3.50 per mile beyond base</li>
          <li><strong>Wait Time:</strong> $25 per 15-minute increment</li>
          <li><strong>Cancellation Fee:</strong> $50 if canceled within 2 hours of pickup</li>
        </ul>
      </div>

      <!-- Zero Tolerance -->
      <div class="rate-card">
        <h2>🚫 Zero-Tolerance Policy</h2>
        <p>
          Vanta Rides LLC enforces a strict zero-tolerance policy against the use of intoxicating substances by drivers.
          If you suspect impairment, report it immediately.
        </p>
        <p>
          <strong>Report concerns:</strong> Email
          <a href="mailto:vantarides@gmail.com">vantarides@gmail.com</a>,
          call <a href="tel:+12145551234">(214) 555-1234</a>,
          or contact <a href="https://dallascityhall.com" target="_blank">DallasCityHall.com</a> or 311.
        </p>
      </div>

      <!-- Accessibility -->
      <div class="rate-card">
        <h2>♿ Wheelchair Accessibility</h2>
        <p>
          We proudly accommodate guests with accessibility needs. Please request wheelchair-accessible options during booking, and we’ll arrange appropriate accommodations.
        </p>
      </div>

      <!-- Complaints -->
      <div class="rate-card">
        <h2>📞 Complaint Reporting</h2>
        <p>
          To report service concerns to the City of Dallas, call 311 or visit
          <a href="https://dallascityhall.com" target="_blank">DallasCityHall.com</a>.
        </p>
        <p>
          You may also contact us directly at
          <a href="mailto:vantarides@gmail.com">vantarides@gmail.com</a> or <a href="tel:+12145551234">(214) 555-1234</a>.
        </p>
      </div>
    </div>

    <p class="disclaimer">*Rates and policies effective as of May 2025.</p>
  </div>
</template>

<script>
export default {
  name: 'RatesView'
};
</script>

<style scoped>
.rates-page {
  max-width: 1000px;
  margin: auto;
  padding: 3rem 2rem;
  background-color: #fff;
  border-radius: 14px;
  box-shadow: 0 0 25px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

h1 {
  font-size: 2.2rem;
  text-align: center;
  margin-bottom: 0.5rem;
}

.subtitle {
  text-align: center;
  font-size: 1.05rem;
  color: #666;
  margin-bottom: 2.5rem;
}

.card-section {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.rate-card {
  background: #fafafa;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.04);
  transition: all 0.3s ease;
}

.rate-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 18px rgba(0, 0, 0, 0.07);
}

.rate-card h2 {
  font-size: 1.3rem;
  color: #c7a008;
  margin-bottom: 1rem;
}

.rate-card ul {
  list-style: none;
  padding-left: 0;
}

.rate-card ul li {
  font-size: 1.05rem;
  margin-bottom: 0.6rem;
  line-height: 1.5;
}

.rate-card p {
  font-size: 1.05rem;
  line-height: 1.7;
  margin-bottom: 1rem;
}

a {
  color: #c7a008;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
  color: #e0b70b;
}

.disclaimer {
  text-align: center;
  margin-top: 2.5rem;
  font-size: 0.9rem;
  color: gray;
}
</style>
