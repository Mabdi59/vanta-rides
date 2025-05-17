import axios from 'axios';

export default {
  changePassword(newPassword) {
    return axios.put('/api/users/password', { newPassword });
  }
};

