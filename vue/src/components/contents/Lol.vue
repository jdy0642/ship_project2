<template>
<div>
   <v-btn style="margin-top:40px;margin-right:40px" @click="createRoom()" top class="float-right" color="blue-grey darken-2">카드생성하기</v-btn>
      <v-tooltip left >
        <span>새로 고침 </span>
      </v-tooltip>   
      <v-btn style="float:left" @click="kal" color="#607D8B">칼바람 GO!</v-btn>
    <br /><br />

    <div style="padding:50px">
   <v-row>
      <v-hover v-slot:default="{ hover }" v-for="(room, i) of rooms" :key="room.cardseq">
      <v-card :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }" class="mx-auto" shaped>
      <v-img
      @click="joinRank(room)"
      style="width:250px;height:350px" 
      :src="require(`@/assets/img/lol/${room.imgurl}.jpg`)">
      <div style="height:150px">
      </div>
      <div style="height:200px;background-image:linear-gradient(to top, rgba(0, 0, 0, 0.6) 85%, transparent 190px)">
      <br />
      <h6>{{room.title}}</h6>
      <h6>방장: {{room.rhost}}</h6>
      <h6>티어: {{room.tier}}</h6>
      <h6>{{btime[i]}}</h6>
      </div>
      </v-img>
      </v-card>
      </v-hover>
      </v-row>
      </div>
       <a color="primary" href="javascript:scroll(0,0)">처음으로 돌아가기 </a>
      <!-- <v-btn  @click="back">처음으로 돌아가기</v-btn> -->
</div>
</template>
<script>
import axios from 'axios'
import {store} from '@/store'
export default {
   computed: {},
   mounted(){
      this.scroll()
   },
   created(){
      this.page === 1
      // this.$moment.locale('ko')
      this.bringlist()
      
   },
   data(){
      return{
         length:'',
         row: 'rank',
         rooms:[],
         temps:{},
         state: store.state,
         ntime: this.$moment(new Date()).format('YYYY-MM-DD HH:MM:SS'),
         btime: [],
         page: 1,
      list: [],
      }
   },
   methods:{
      scroll(){
         window.onscroll = () => {
            let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) + window.innerHeight === document.documentElement.offsetHeight
            if (bottomOfWindow) {
            this.scrolledToBottom = true // replace it with your code
            this.bringlist()
            }
         }
      },
      joinRank(param){
         store.state.selectGame = param
         this.$router.push({name : 'joinrank', params:{ game: param.cardseq}})
      },
      bringlist(){
      let url = `/lol/listpage=${this.page}`
      let data = {
         page: this.page
      }
      let headers = {
              'authorization': 'JWT fefege..',
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
                }
      axios
           .get(url, data, headers)
           .then(res =>{
            this.list = res.data
            if((this.list.length)%9===0){
            this.rooms = this.list
            this.page += 1
            
            }else{
               this.rooms = this.list
               alert('마지막 페이지입니다.')
               window.onscroll = null
            }
              this.rooms = res.data
              this.timechange()
           })
           .catch(e=>{
              alert('AXIOS FAIL'+e)
           })
      },
      kal(){
         alert('칼바람은 준비중입니다.')
      },
      createRoom(){
         this.$router.push({path:'/createRoom'})
      },
      timechange(){
         for(let i=0;i<this.rooms.length;i++){
            this.btime.push(this.$moment(this.rooms[i].wtime).fromNow())
         }

         // this.btime = this.$moment(x).fromNow()
      }
   }
}
</script>
<style scoped>
.v-card {
  transition: opacity .4s ease-in-out;
  margin-top:10px;
}

.v-card:not(.on-hover) {
  opacity: 0.7;
 }

</style>