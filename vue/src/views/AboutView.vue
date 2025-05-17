<template>
  <section class="about-page">
    <div class="intro">
      <h1>Driven by Elegance. Defined by Service.</h1>
      <p>
        At Vanta Rides LLC, we don’t just drive — we deliver an experience. Founded in Dallas, TX, our premium black SUV service revolves around one luxurious Cadillac Escalade ESV and one unwavering commitment to excellence.
      </p>
    </div>

    <div class="highlights">
      <div class="highlight card-hover">
        <h2>🎯 One Vehicle. One Standard.</h2>
        <p>
          You’re not getting a random driver from a fleet. You're getting the same professional, punctual, and polished ride every time — with a name and face you know.
        </p>
      </div>

      <div class="highlight card-hover">
        <h2>🛬 Airport, Events & VIP Transfers</h2>
        <p>
          Whether you're arriving at DFW, heading to a gala, or hosting a business client, Vanta ensures you arrive relaxed, on time, and in luxury.
        </p>
      </div>

      <div class="highlight card-hover">
        <h2>✨ The Cadillac Escalade ESV</h2>
        <p>
          Experience executive-level comfort in a vehicle built to impress — with rich leather seats, tinted privacy, surround sound, and room for up to six passengers.
        </p>
      </div>

      <div class="highlight card-hover">
        <h2>♿ Safety, Accessibility & Ethics</h2>
        <p>
          We offer wheelchair-accessible service upon request and operate under a strict zero-tolerance policy for drugs and alcohol. Vanta Rides complies with all City of Dallas transportation-for-hire regulations.
        </p>
        <router-link to="/rates" class="inline-link">View Our Rates & Policies</router-link>
      </div>
    </div>

    <div class="closing">
      <p>
        When you choose Vanta Rides, you’re choosing more than transportation — you’re choosing a personal, premium, and professional service designed to elevate every mile.
      </p>
      <router-link to="/book" class="cta-button">Reserve Your Ride</router-link>
    </div>
  </section>
</template>

<script>
export default {
  name: 'AboutView'
};
</script>

<style scoped>
.about-page {
  max-width: 980px;
  margin: auto;
  padding: 3rem 2rem;
  background-color: #ffffff;
  border-radius: 14px;
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.05);
  color: #222;
  font-family: 'Segoe UI', sans-serif;
}

.intro h1 {
  font-size: 2.5rem;
  text-align: center;
  margin-bottom: 1.2rem;
  color: #000;
}

.intro p {
  font-size: 1.1rem;
  text-align: center;
  max-width: 700px;
  margin: 0 auto 2.5rem;
  line-height: 1.7;
}

.highlights {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.highlight {
  background-color: #f8f8f8;
  border-left: 5px solid #c7a008;
  padding: 1.5rem 1.75rem;
  border-radius: 12px;
  transition: all 0.3s ease;
}

.highlight h2 {
  font-size: 1.35rem;
  margin-bottom: 0.7rem;
  color: #111;
}

.highlight p {
  font-size: 1.05rem;
  line-height: 1.6;
  color: #444;
}

.inline-link {
  display: inline-block;
  margin-top: 0.9rem;
  color: #c7a008;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.25s ease;
}

.inline-link:hover {
  color: #a88900;
  text-decoration: underline;
}

.closing {
  margin-top: 3.5rem;
  text-align: center;
}

.closing p {
  font-size: 1.15rem;
  margin-bottom: 1.5rem;
  color: #222;
}

.cta-button {
  background-color: #c7a008;
  color: black;
  padding: 0.75rem 2rem;
  border-radius: 6px;
  font-weight: bold;
  text-decoration: none;
  transition: all 0.3s ease;
  display: inline-block;
}

.cta-button:hover {
  background-color: #e0b70b;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.card-hover {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
}
</style>
