<template>
<div>
  <v-row >
    <v-responsive style="width:300px;margin-left:30px;margin-top:30px">
      <v-card style="margin-right:5px;margin-top:5px">
      <img style="width:140px;margin-top:20px;" :src="temp.photo" alt="" />
        <v-card-subtitle>
        <v-icon style="margin:20px;">mdi-crown</v-icon>
          <v-card-text>소환사명 : {{temp.summonername}}</v-card-text>
          <v-card-text center>티어 : {{temp.tier}}</v-card-text>
          <v-card-text>승률 : {{temp.rate}}</v-card-text>
        </v-card-subtitle>
      </v-card>
    </v-responsive>

  <v-responsive style="width:300px;height:500px;margin-left:30px;margin-top:30px">
    <v-card style="margin-right:5px;margin-top:5px;width:300px;height:400px">
      <v-btn fab dark color="indigo" style="margin-top:150px" >
        <v-icon dark>mdi-plus</v-icon>
      </v-btn>
    </v-card>
  </v-responsive>

  
  <v-responsive >
    <iframe src="http://192.168.5.46:3000" style="margin-top:30px" width="410px" height="500px" frameborder="0"></iframe>
    
        <!-- <v-flex style="margin-right:5px; margin-top:5px">
          <v-card color="grey lighten-3">
            <v-toolbar dark color="primary darken-1">
              <v-toolbar-title center>채팅</v-toolbar-title>
            </v-toolbar>
            <v-card-text>
        <v-list ref="chat" id="logs">
          <template v-for="(item, index) of logs">
            <v-subheader v-if="item" :key="index">{{ item }}</v-subheader>
          </template>
        </v-list>
            </v-card-text>
            <v-card-actions>
              <v-form @submit.prevent="submit">
              <v-text-field  xs12 v-model="msg" label="Message" single-line solo-inverted class="d-flex"></v-text-field>
              <v-btn x-large class="float-right" fab dark small color="primary" type="submit">
                <v-icon small dark>send</v-icon>
              </v-btn>
                </v-form>
            </v-card-actions>
          </v-card>
        </v-flex> -->
  </v-responsive>
  </v-row>
</div>

</template>
<script>
import axios from "axios"
import {store} from "@/store"
export default {
  data(){
    return {
      state:store.state,
      // logs: [],
      // msg: null,
      context:'http://localhost:8080',
      temp:''
    }
  },
  methods: {
    // submit() {
    //   this.logs.push(this.msg);
    //   this.msg = "";
    // },
    lol(){
      this.$router.push({path:'/lol'})
    }
  },
  created(){
    let url = `${this.context}/crawl/summoner/userName=${this.state.person.summonername}`
    axios
    .get(url)
    .then(res=>{
      this.temp = res.data[0]
      this.summonername = res.data[0].summonername
    })
    let url2 = `http://192.168.5.46:3000`
    axios
    .get(url2)
    
  },
  // watch: {
  //   logs() {
  //     setTimeout(() => {
  //       this.$refs.chat.$el.scrollTop = this.$refs.chat.$el.scrollHeight;
  //     }, 0);
  //   }
  // }
}
</script>
<style scoped>
#logs {
  height: 230px;
}

</style>