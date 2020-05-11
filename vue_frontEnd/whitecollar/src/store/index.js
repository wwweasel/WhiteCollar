import Vue from "vue";
import Vuex from "vuex";
import table from './modules/table';
import 'es6-promise/auto'


Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    table
  }
});
