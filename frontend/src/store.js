import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        pid: '',
        mid:''
    },
    mutations: {
        setpid (state,data) {
            state.pid=data;
        },
        setmid (state,data) {
            state.mid=data;
        }
    }
})
