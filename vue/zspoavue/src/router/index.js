import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Mycomponent from '@/components/Mycomponent'

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
    }
  ]
})
