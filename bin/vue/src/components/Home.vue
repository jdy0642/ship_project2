<template>
<div id="app">
<layout>
   <template #header="h" >
    <v-app id="inspire" style="height:1010px;" >
  <!-- --------------------------------------- 네비 ------------------------------------------ -->
      <div >
        <v-toolbar fixed class="sticky" color="indigo darken-1 " >
        <!-- <v-toolbar color="#3F51B5" :src="'https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg'"> -->
          <v-toolbar-title class="white--text" style="margin-left:300px;" @click="home()" > 
            <v-icon large color="white">mdi-vuetify</v-icon>
              SHIP 
          </v-toolbar-title>

        <v-spacer></v-spacer>
          <v-toolbar-items  style="margin-right:225px;" >

            <!-- <v-row style="margin-right:20px;">
              <v-badge :value="hover" color="deep-purple accent-4" left offset-x="100" offset-y="20"
                        content="9999+" transition="slide-x-transition">
                <v-hover v-model="hover">
                  <v-btn text style="font-size:15px;margin-top:12px" class="white--text" @click="test()"   >TEST</v-btn>
                </v-hover>
              </v-badge>
            </v-row> -->
  
            <v-row style="margin-right:85px; margin-top:12px;" v-if="!authCheck">
            </v-row>

            <v-row v-else>
              <v-btn text style="font-size:15px;margin-top:12px" class="white--text" @click="register()">구장 등록</v-btn>
            </v-row>
            <v-row style="margin-right:85px; margin-top:12px;" v-if="!authCheck">
            </v-row>

            <v-row v-else>
              <v-btn text style="font-size:15px;margin-top:12px" class="white--text" @click="membermanage()">회원 관리</v-btn>
            </v-row>
            <v-row style="margin-right:85px; margin-top:12px;" v-if="!authCheck">
            </v-row>

            <v-row v-else>
              <v-btn text style="font-size:15px;margin-top:12px" class="white--text" @click="revenuemanage()">수익 관리</v-btn>
            </v-row>

            <v-row style="margin-right:85px; margin-top:12px;" v-if="!authCheck">
              <join></join>
            </v-row>

            <v-row v-else>
              <v-btn text style="font-size:15px;margin-top:12px" class="white--text" @click="mypage()">MY PAGE</v-btn>
            </v-row>

            <v-row style="margin-right:85px ; margin-top:12px"  v-if="!authCheck">
                <login></login>
            </v-row>

            <v-row style="margin-right:5px ; margin-top:12px" v-else >
                <v-btn text style="font-size:15px" class="white--text" @click="logout()">LOGOUT</v-btn>
            </v-row>

            <!-- <v-overflow-btn :items="contents" label="CONTENTS" class="white--text"></v-overflow-btn> -->

            <div class="text-center">
              <v-menu offset-y>
                <template v-slot:activator="{ on }">
                  <v-btn style="margin-top:12px;font-size:15px;margin-right:70px" color="indigo darken-1" v-on="on">  Contents  </v-btn>
                </template>
                <v-list>
                  <v-list-item v-for="(item) of items" :key="item.title" @click="contgo(item.link)">
                      <v-list-item-title>{{ item.title }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div >

            <!-- <v-col class="d-flex" cols="6" sm="4">
              <v-select :items="items" label="CONTENTS" class="white--text"></v-select>
            </v-col> -->
            
          </v-toolbar-items>
        <!-- <template v-if="$vuetify.breakpoint.smAndUp" >
          <v-btn icon>
            <v-icon >mdi-export-variant</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-delete-circle</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon >mdi-plus-circle</v-icon>
          </v-btn>
        </template> -->
        </v-toolbar>
      </div>
    </v-app>
  </template>
   <!-- --------------------- 컨텐츠  ------------------------- -->
<template #content ="c">
  <div id="app" style="width:67%;"  >
    <v-app id="inspire" >
        <v-card>
        <v-navigation-drawer  width="100%" >
          <template>
            <router-view></router-view>
          </template>
        </v-navigation-drawer>
      </v-card>
    </v-app>
  </div>
</template>
<template #footer ="f">
</template>
  </layout>
</div>
</template>

<script>
// import mdiAccount from '@mdi/js'
import Login  from '@/components/auth/Login.vue'
import Join  from '@/components/auth/Join.vue'
import Layout from '@/components/cmm/Layout.vue'
import {store} from '@/store'
export default {
  components:{
    Layout, Login, Join
  },
  data(){
   return{
     items: [
      { title: 'LOL' ,link:'/lol'},
      { title: 'FUTSAL', link:'/futsal' },
    ],
     contents: [
        { text: 'LOL' },
        { text: 'FUTSAL' },
      ],
      sides: [
          { title: 'Home', icon: 'mdi-home', link:'/'},
          { title: '구장 등록', icon: 'mdi-account-group-outline', link:'/register' },
          { title: '회원 관리', icon: 'mdi-account-group-outline', link:'/membermanage' },
        ],
      state:store.state,
      hover: false,
      }
  },
  methods:{
    home(){
      this.$router.push({path:'/'})
    },
    logout(){
      this.state.person={}
      this.state.authCheck = false
      this.$router.push({path:'/'})
    },
    mypage(){
      this.$router.push({path:'/mypage'})
    },
    register(){
      this.$router.push({path:'/register'})
    },
    membermanage(){
      this.$router.push({path:'/membermanage'})
    },
    revenuemanage(){
      this.$router.push({path:'/revenuemanage'})
    },
    test(){
      this.$router.push({path:'/test'})
    },
    admin(){
      this.$router.push({path:'/admin'})
    },
    lol(){
      this.$router.push({path:'/lol'})
    },
    futsal(){
      this.$router.push({path:'/futsal'})
    },
    sidego(x){
      this.$router.push({path:`${x}`})
    },
    contgo(x){
      this.$router.push({path:`${x}`})
    },
    
  },
  computed:{
    authCheck(){
      return store.state.authCheck}
  }
}
</script>
<style scoped>
#app{
  theme:black;
}
</style>