import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from '@/router'
import vuex from 'vuex'
import {store} from '@/store'
import VSwitch from 'v-switch-case'
import axios from 'axios'
import moment from 'moment'
import VueMonentJS from 'vue-moment'

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(VSwitch,VueMonentJS,moment)

new Vue({
  vuetify,
  router,
  vuex,
  store,
  moment,
  VueMonentJS,
  render: h => h(App)
}).$mount('#app')
