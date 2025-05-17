import axios from 'axios';

export default {
  getAllVehicles() {
    return axios.get('/api/vehicles');
  }
};
