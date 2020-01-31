<template>
<div>
   <v-btn style="margin-top:40px;margin-right:40px" @click="createRoom()" top class="float-right" color="blue-grey darken-2">카드생성하기</v-btn>
      <v-tooltip left >
         <template v-slot:activator="{ on }">
            <v-icon v-on="on" style="margin-top:40px;margin-right:10px " 
               @click="refresh()" x-large top absolute class="float-right" color="white">mdi-refresh</v-icon>
         </template>
        <span>새로 고침 </span>
      </v-tooltip>   
      <v-btn style="float:left" @click="kal" color="#607D8B">칼바람 GO!</v-btn>
    <br /><br />

    <div style="padding:50px">
   <v-row>
      <v-hover v-slot:default="{ hover }" open-delay="0" v-for="room of rooms" :key="room.cardseq">
      <v-card :elevation="hover ? 16 : 2" class="mx-auto" style="margin-top:10px">
      
      <v-img
      @click="joinRank(room)"
      style="width:250px;height:350px" 
      :src="require(`${room.imgurl}`)">
      <div style="height:150px">
      </div>
      <div style="height:200px;background-image:linear-gradient(to top, rgba(0, 0, 0, 0.6) 85%, transparent 190px)">
      <br />
      <h6>{{room.title}}</h6>
      <h6>방장: {{room.rhost}}</h6>
      <h6>티어: {{room.tier}}</h6>
      <h6>{{timechange(room.wtime)}}{{btime}}</h6>
      <v-img style="width:30px;" src="https://www.mobachampion.com/static/imgs/mid_icon.59083eeab24c.png"></v-img>
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
         btime: '',
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
      let url = `http://localhost:8080/lol/listpage=${this.page}`
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
            // this.temps = this.list.slice((this.page-1)*9,(this.page-1)*18)
            // this.rooms.push(...this.temps)
            this.rooms = this.list
            this.page += 1
            }else{
               this.rooms = this.list
               alert('마지막 페이지입니다.')
               window.onscroll = null
            }
              this.rooms = res.data
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
      timechange(x){
         // this.$moment(this.ntime  - this.$moment(wtime)).format('yyyy-MM-dd HH:mm:ss')
         
         this.btime = this.$moment(x).fromNow()
         
         // this.$moment(x)
         // this.$moment(x).format('YYYY-MM-DD HH:MM:SS')
      }
   }
}
</script>
<style scoped>
#d{
   height: 50%;
   
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}

</style>