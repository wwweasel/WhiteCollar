import Vue from "vue";
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import TableB from '../TableB.vue'
import Shop from '../Shop.vue'


const routes = [
    {path: '/', component: TableB},
    {path: '/store/:shopId', component: Shop, props: true}
];
  
export default new VueRouter({
    routes: routes,
    mode: 'history'
});