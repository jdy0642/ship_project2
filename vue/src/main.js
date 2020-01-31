import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from '@/router'
import vuex from 'vuex'
import {store} from '@/store'
import VSwitch from 'v-switch-case'
import axios from 'axios'
import VueMonentJS from 'vue-momentjs'
import moment from 'moment'
import "moment/locale/ko.js"
import map from 'vue-daum-map'

// import VueSocketIOExt from 'vue-socket.io-extended';
// import io from 'socket.io-client';
// const socket = io('http://localhost:3000');
// Vue.use(VueSocketIOExt, socket);

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(VSwitch)
Vue.use(VueMonentJS,moment)
Vue.use(map)

new Vue({
  vuetify,
  router,
  vuex,
  store,
  moment,
  VueMonentJS,
  render: h => h(App)
}).$mount('#app')
