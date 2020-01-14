<template>
<div>
  <v-row>
  <v-responsive style="width:150px height:200px">
  <v-card style="margin-right:5px; margin-top:5px;">
  <v-img style="width:300px" src="https://ww.namu.la/s/37a84569eff79fb982afb54f6d411b0a4e3212dda379fb3f326634358a13e36c84b8e0e379c44c288021002eecd2ae2ba479f92d3052a8afacddea23d73d69fe2aace5fb39279de96cf368f2f99ea96cfe451fed8a88a8e7731fc6c7d6893680"></v-img>
   <v-card-subtitle>
   <v-icon style="margin:20px;">mdi-crown</v-icon>
   <v-icon v-bind:title="msg1">mdi-key</v-icon>
            <v-card-text>소환사명 : yamine</v-card-text>
               <v-card-text center>티어 : gold4</v-card-text>
          <v-card-text>승률 : 55%</v-card-text>
          <v-card-text>최근 10경기 전적 : 4승6패</v-card-text>
         </v-card-subtitle>
  </v-card>
  </v-responsive>
  <v-responsive style="width:150px height:200px">
  <v-card style="margin-right:5px; margin-top:5px">
  <!--사용자가 가장 많이 사용하는 챔피언을 api에서 받을지 본인의 프로필사진을 넣을지 -->
  <v-img style="width:300px" src="https://ww.namu.la/s/7d709a5fdb50121194c88d2ad039608f0651d38a84008b1ce2361a74e65ca77743c0c2e981b0256ca7d704be1602ea61f89d87525ba11f7537631ce1c458de536bef4015f17c3c1af672e4867351fec647c4348e6343c5ea6e71818951caf42c"></v-img>
   <v-card-subtitle dark>
   <v-icon style="margin:20px;">참가자</v-icon>
            <v-card-text>소환사명 : 거울보면문도</v-card-text>
               <v-card-text center>티어 : gold4</v-card-text>
          <v-card-text>승률 : 55%</v-card-text>
          <v-card-text>최근 10경기 전적: 4승6패</v-card-text>
           
         </v-card-subtitle>
  </v-card>
  </v-responsive>
  
  <v-content >
    
        <v-flex style="margin-right:5px; margin-top:5px">
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
        </v-flex>
        <v-icon @click="lol()">mdi-reply</v-icon><h4 @click="lol()">뒤로가기</h4>

  </v-content>
  </v-row>
</div>

</template>
<script>
export default{
  data(){
    return {
      logs: [],
      msg: null,
      msg1: '방장위임'
    }
  },
  methods: {
    submit() {
      this.logs.push(this.msg);
      this.msg = "";
    },
    lol(){
      this.$router.push({path:'/lol'})
    }
  },
  watch: {
    logs() {
      setTimeout(() => {
        this.$refs.chat.$el.scrollTop = this.$refs.chat.$el.scrollHeight;
      }, 0);
    }
  }
}
</script>
<style scoped>
#logs {
  height: 230px;
}
#app{
 
}

</style>