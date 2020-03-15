import store from '../store'
import LoginPage from '../views/Login'
import RegisterPage from '../views/Register'
import Home from '../views/Stutents'

export default [


    /* Geral */
    {
        path: '/',
        name: 'Home',
        component: Home,
        beforeEnter (to, from, next) {
            if(!store.getters["auth/isAuthenticated"]){
                next("Login")
            }else{
                next()
            }
        }
    },
    {
        path: '/login',
        meta: { 
            public: true,
        },
        props: true,
        name: 'Login',
        component: LoginPage
    },{
        path:'/register',
        meta: {
            public: true,
        },
        name:"Register",
        component:RegisterPage
    },

    {
        path: '/logout',
        
        name: 'Logout',
        beforeEnter (to, from, next) {
            store.dispatch('auth/logout', true)
            next('Login')
        },
        
    },

];
