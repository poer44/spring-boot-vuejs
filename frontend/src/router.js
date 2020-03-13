import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'
import User from '@/components/User'
import Newmission from '@/components/step/Newmission'
import Newproject from '@/components/step/Newproject'
import Adddata from '@/components/step/Adddata'
import Enhancedata from '@/components/step/Enhancedata'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Hello',
            component: Hello
        },
        {
            path: '/callservice',
            name: 'Service',
            component: Service
        },
        {
            path: '/bootstrap',
            name: 'Bootstrap',
            component: Bootstrap
        },
        {
            path: '/user',
            name: 'User',
            component: User
        },
        {
            path: '/newmission',
            name: 'Newmission',
            component: Newmission
        },
        {
            path: '/newproject',
            name: 'Newproject',
            component: Newproject
        },
        {
            path: '/adddata',
            name: 'Adddata',
            component: Adddata
        },
        {
            path: '/enhancedata',
            name: 'Enhancedata',
            component: Enhancedata
        }
    ]
})
