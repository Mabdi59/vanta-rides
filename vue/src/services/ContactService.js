const API_URL = 'http://localhost:9000/api/contact';

export default {
  async sendContactMessage(form) {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(form)
    });

    const data = await response.json();

    if (!response.ok) {
      const errorMsg = data?.message || 'Failed to send message';
      throw new Error(errorMsg);
    }

    return data;
  }
};
