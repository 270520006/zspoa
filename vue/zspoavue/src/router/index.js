import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Mycomponent from '@/components/Mycomponent'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Login from '@/components/Login';


Vue.use(ElementUI);
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/Mycomponent',
      name: 'Mycomponent',
      component: Mycomponent
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    }
  ]
})
