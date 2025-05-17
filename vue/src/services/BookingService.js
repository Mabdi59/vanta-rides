import axios from 'axios';

const BookingService = {
  createBooking(data) {
    return axios.post('/api/bookings', data);
  },

  getAllBookings() {
    return axios.get('/api/bookings');
  },

  updateBookingStatus(requestId, newStatus) {
    return axios.put(`/api/bookings/${requestId}/status`, {
      status: newStatus
    });
  }
};

export default BookingService;
