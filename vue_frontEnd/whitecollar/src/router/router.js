import Vue from "vue";
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import TableB from '../TableB.vue'
import Store from '../Store.vue'


const routes = [
    {path: '/', component: TableB},
    {path: '/store/:storeId', component: Store, props: true}
];
  
export default new VueRouter({
    routes: routes,
    //mode: 'history'
});