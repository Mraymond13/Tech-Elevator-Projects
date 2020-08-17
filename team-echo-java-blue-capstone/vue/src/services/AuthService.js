import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  userAddsImage(user, user_image) {
    let payload = {id:user.id, user_image}
    return axios.put(`/profile/${user.id}`, payload)
  },

  getUser(user) {
    return axios.get(`/getUsers/${user.id}`)
  }

} 
