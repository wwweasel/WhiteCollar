import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router/router";
import Vuelidate from 'vuelidate'


import {BootstrapVue,BootstrapVueIcons} from 'bootstrap-vue';///dist/bootstrap-vue.esm
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons)
Vue.use(Vuelidate)

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount("#app");
