import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
      person:{},
      matchList:{},
      authCheck : false
  }
})