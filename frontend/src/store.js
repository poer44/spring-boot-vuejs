import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        pid: '',
        mid: '',
        mission_details: {}
    },
    mutations: {
        setpid(state, data) {
            state.pid = data;
        },
        setmid(state, data) {
            state.mid = data;
        },
        setmretail(state, data) {
            state.mission_details["mid"] = state.mid;
            for (let key  in data) {
                state.mission_details[key] = data[key];
            }
            console.log(state);
        }
    },
    plugins: [createPersistedState()]
})
