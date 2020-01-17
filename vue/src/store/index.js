import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    context: 'http://localhost:8080',
		header: {'authorization': 'JWT fefege..',
      'Accept' : 'application/json',
      'Content-Type': 'application/json',
      "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36",
      "Access-Control-Allow-Origin": '*'},
    person:{},
    matchList:{},
    selectMatch:{},
    authCheck : false,
  }
})