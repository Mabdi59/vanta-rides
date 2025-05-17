<template>
  <section class="contact-page card-hover">
    <h1>Contact Us</h1>
    <p class="subtitle">If you have questions or need to book by phone, please reach out below.</p>

    <div class="contact-container">
      <!-- Map & Info -->
      <div class="contact-left">
        <div class="map-wrapper">
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2689.219385810782!2d-96.80567048447826!3d32.77916738096838!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x864e9914f3eeb96f%3A0xc72a3f96e2d62a7!2sDowntown%20Dallas%2C%20Dallas%2C%20TX!5e0!3m2!1sen!2sus!4v1715420000000"
            width="100%"
            height="200"
            style="border:0;"
            allowfullscreen
            loading="lazy"
            referrerpolicy="no-referrer-when-downgrade">
          </iframe>
        </div>

        <div class="contact-info">
          <p><strong>Vanta Rides LLC</strong></p>
          <p>Downtown Dallas, TX</p>
          <p>Email: <a href="mailto:vantarides@gmail.com">vantarides@gmail.com</a></p>
          <p>Phone: <a href="tel:+12145551234">(214) 555-1234</a></p>
        </div>
      </div>

      <!-- Form -->
      <div class="contact-form">
        <form @submit.prevent="submitForm">
          <div class="form-group">
            <label for="name">Name *</label>
            <input type="text" id="name" v-model="form.name" required />
          </div>

          <div class="form-group">
            <label for="email">Email *</label>
            <input type="email" id="email" v-model="form.email" required />
          </div>

          <div class="form-group">
            <label for="message">Message *</label>
            <textarea id="message" v-model="form.message" required></textarea>
          </div>

          <div class="form-group">
            <p class="recaptcha-note">[reCAPTCHA placeholder]</p>
          </div>

          <LoadingSpinner v-if="isLoading" />

          <button type="submit" :disabled="isLoading">
            {{ isLoading ? 'Sending...' : 'Submit' }}
          </button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import ContactService from '@/services/ContactService';
import LoadingSpinner from '@/components/LoadingSpinner.vue';

export default {
  name: 'ContactView',
  components: { LoadingSpinner },
  data() {
    return {
      form: {
        name: '',
        email: '',
        message: ''
      },
      isLoading: false
    };
  },
  methods: {
    async submitForm() {
      this.isLoading = true;
      try {
        const response = await ContactService.sendContactMessage(this.form);
        this.$emit('toast', response.message || 'Message sent successfully!', 'success');
        this.form.name = '';
        this.form.email = '';
        this.form.message = '';
      } catch (error) {
        console.error('Error submitting message:', error);
        this.$emit('toast', error.message || 'There was an error sending your message. Please try again.', 'error');
      } finally {
        this.isLoading = false;
      }
    }
  }
};
</script>

<style scoped>
.contact-page {
  max-width: 1000px;
  margin: auto;
  padding: 3rem 2rem;
  background-color: #fff;
  border-radius: 14px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.06);
  transition: all 0.3s ease;
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 0.5rem;
}

.subtitle {
  text-align: center;
  margin-bottom: 2rem;
  color: #666;
  font-size: 1.05rem;
}

.contact-container {
  display: flex;
  flex-wrap: wrap;
  gap: 2.5rem;
}

.contact-left,
.contact-form {
  flex: 1;
  min-width: 300px;
}

.map-wrapper {
  overflow: hidden;
  border-radius: 10px;
  box-shadow: 0 4px 14px rgba(0, 0, 0, 0.05);
}

.contact-info {
  margin-top: 1.25rem;
  font-size: 1rem;
  line-height: 1.7;
}

.contact-info a {
  color: #c7a008;
  text-decoration: none;
}

.contact-info a:hover {
  text-decoration: underline;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  font-weight: bold;
  display: block;
  margin-bottom: 0.5rem;
}

input,
textarea {
  width: 100%;
  padding: 0.75rem;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 1rem;
  transition: all 0.25s ease;
  background-color: #fafafa;
}

input:focus,
textarea:focus {
  outline: none;
  border-color: #c7a008;
  box-shadow: 0 0 6px rgba(199, 160, 8, 0.4);
  background-color: #fff;
}

.recaptcha-note {
  font-size: 0.9rem;
  color: gray;
  margin: 0;
}

button {
  width: 100%;
  padding: 1rem;
  background-color: #c7a008;
  color: black;
  font-weight: bold;
  font-size: 1rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.3s ease;
}

button:hover:not(:disabled) {
  background-color: #e0b70b;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

/* Hover lift effect */
.card-hover {
  transition: all 0.3s ease;
}
.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
}
</style>
