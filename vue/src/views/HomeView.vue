<template>
  <div class="home">
    <!-- Hero Section -->
    <section class="hero">
      <div class="overlay fade-in-hero">
        <div class="hero-content">
          <p class="badge">Est. 2024 — Private Black SUV</p>
          <h1>Arrive in Style. Every Time.</h1>
          <p>Luxury Cadillac Escalade transportation service across Dallas–Fort Worth.</p>
          <router-link to="/book" class="cta-button">Reserve Now</router-link>
        </div>
      </div>
    </section>

    <!-- Fleet Highlight -->
    <section class="fleet-highlight section-padding">
      <h2>Meet the Ride</h2>
      <div class="fleet-layout">
        <div class="fleet-text">
          <h3>Cadillac Escalade ESV</h3>
          <p>
            Whether you're headed to DFW Airport or Downtown, this luxury SUV delivers unmatched comfort,
            privacy, and elegance — driven by a professional who understands discretion and punctuality.
          </p>
          <ul class="feature-list">
            <li>✨ Bold, commanding design</li>
            <li>🛋️ Plush leather seating for up to 6 passengers</li>
            <li>🎵 Premium surround sound and USB charging</li>
            <li>🧳 Roomy luggage space for all your essentials</li>
          </ul>
        </div>
        <div class="fleet-image card-hover">
          <img
            src="https://www.autotrader.com/wp-content/uploads/2023/10/2024-cadillac-escalade-luxury-front-3qtr.jpg"
            alt="Cadillac Escalade ESV"
          />
          <router-link to="/book" class="cta-button secondary">Book the Escalade</router-link>
        </div>
      </div>
    </section>

    <!-- Testimonials -->
    <section class="testimonials section-padding">
      <h2>What Clients Say</h2>
      <div class="quote-cards">
        <div class="quote-card card-hover">“Felt like a VIP the whole ride. Impeccable service.”</div>
        <div class="quote-card card-hover">“Punctual, elegant, and smooth from start to finish.”</div>
        <div class="quote-card card-hover">“Hands-down the best black car ride I’ve ever had in Dallas.”</div>
      </div>
    </section>

    <!-- About -->
    <section class="about section-padding">
      <h2>About Vanta Rides</h2>
      <p>
        Vanta Rides LLC is a Dallas-based luxury SUV service founded on a commitment to safety, comfort,
        and consistent professionalism. One vehicle, one driver — and a promise to elevate your journey.
      </p>
    </section>
  </div>
</template>

<style scoped>
.home {
  font-family: 'Segoe UI', sans-serif;
  color: #222;
}

/* HERO */
.hero {
  height: 92vh;
  background: url('https://th.bing.com/th/id/R.593a847a620a3b59a68f69b5f9e4c295?rik=he%2bwtgzn3qtzhA&riu=http%3a%2f%2fwww.marinoperformancemotors.com%2fimagetag%2f13285%2f10%2fl%2fUsed-2019-Cadillac-Escalade-Luxury.jpg&ehk=XjpjreGOinmGIWNXLuDjvhQChzey2elDfmC%2fLNZgVv8%3d&risl=&pid=ImgRaw&r=0')
    no-repeat center center/cover;
  position: relative;
}
.overlay {
  background: rgba(0, 0, 0, 0.6);
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.hero-content {
  text-align: center;
  color: white;
  padding: 2rem;
}
.hero-content h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
  text-shadow: 0 0 10px rgba(255, 255, 255, 0.3);
}
.hero-content p {
  font-size: 1.2rem;
  margin-bottom: 2rem;
}

.badge {
  font-size: 0.9rem;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 0.4rem 0.8rem;
  border-radius: 4px;
  display: inline-block;
  margin-bottom: 1rem;
  letter-spacing: 0.5px;
}

/* CTA Button */
.cta-button {
  background-color: #c7a008;
  color: black;
  padding: 0.75rem 2rem;
  border-radius: 6px;
  font-weight: bold;
  text-decoration: none;
  transition: all 0.3s ease;
}
.cta-button:hover {
  background-color: #e0b70b;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}
.cta-button.secondary {
  background: black;
  color: white;
  margin-top: 1.5rem;
}

/* Fleet */
.fleet-highlight {
  background: #f8f8f8;
  text-align: center;
}
.section-padding {
  padding: 4rem 2rem;
}
.fleet-layout {
  display: flex;
  flex-wrap: wrap;
  gap: 2.5rem;
  justify-content: center;
  align-items: center;
  margin-top: 2rem;
}
.fleet-text {
  max-width: 480px;
  text-align: left;
}
.feature-list {
  list-style: none;
  padding-left: 0;
  margin-top: 1.5rem;
}
.feature-list li {
  font-size: 1.05rem;
  margin-bottom: 0.75rem;
}
.fleet-image img {
  width: 400px;
  border-radius: 10px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
}

/* Testimonials */
.testimonials {
  background: white;
  text-align: center;
}
.quote-cards {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  max-width: 700px;
  margin: auto;
}
.quote-card {
  background: #fafafa;
  border-left: 5px solid #c7a008;
  padding: 1rem 1.5rem;
  font-style: italic;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.03);
  transition: all 0.3s ease;
}

/* About */
.about {
  background: #f1f1f1;
  text-align: center;
}
.about p {
  max-width: 800px;
  margin: auto;
  font-size: 1.1rem;
  line-height: 1.6;
}

/* Shared Hover */
.card-hover {
  transition: all 0.3s ease;
}
.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

/* Hero Animation */
.fade-in-hero {
  animation: fadeHero 1s ease-in;
}
@keyframes fadeHero {
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
