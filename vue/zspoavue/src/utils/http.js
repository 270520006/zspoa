import axios from 'axios'
// import Vue from 'vue'
// import router from './router/index.js'

const http=axios.create({
    baseURL:'http://zspoa.com/api/'
})

export default http;