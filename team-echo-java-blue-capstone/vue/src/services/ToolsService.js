import axios from 'axios';
import store from '@/store/index.js'

export default {

    getTools() {
        return axios.get('/tools');
    },
    getToolsByCategories() {
        return axios.get('/categories')
    },
    addToTools(user) {
        const t = [];
        store.state.cart.forEach(element => {
            t.push(element.tool_id);
        });

        return axios.put(`/users/${user.id}/tools`,  t)
    },
    getToolsAtId(user) {
        return axios.get(`/tools/${user.id}`)
    },

    getCheckedOutTools() {
        return axios.get('/checkedout')
    },
    toolReturned(tool) {

        return axios.put(`/returns/${tool.tool_id}`)
    },

    email(tool){
        return axios.post(`/emails/${tool.tool_id}`)
    },

    addReservationToTable(user,tool) {
        return axios.post(`/users/${user.id}/tools/${tool.tool_id}/reservations`)
    }

}