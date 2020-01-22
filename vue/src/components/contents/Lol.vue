<template>
<div style="height:980px">
   <v-btn style="margin-top:40px;margin-right:40px" @click="createRoom()" top class="float-right" color="blue-grey darken-2">카드생성하기</v-btn>
      <v-tooltip left >
         <template v-slot:activator="{ on }">
            <v-icon v-on="on" style="margin-top:40px;margin-right:10px " 
               @click="lol()" x-large top absolute class="float-right" color="white">mdi-refresh</v-icon>
         </template>
        <span>새로 고침 </span>
      </v-tooltip>   
   <v-container fluid>
      <v-btn style="float:left" @click="kal" color="#607D8B">칼바람 GO!</v-btn>
    </v-container>
    <br /><br />
    <div style="padding:50px">
   <v-row>

      
      <v-hover v-slot:default="{ hover }" open-delay="0" v-for="room of rooms" :key="room.cardseq">
      <v-card :elevation="hover ? 16 : 2" id="d" class="mx-auto" style="margin-top:10px"> 
      <v-img
      @click="joinRank()"
      style="width:250px;height:350px" 
      :src="room.imgurl">
      <div style="height:180px">
      </div>
      <div style="height:170px;background-image:linear-gradient(to top, rgba(0, 0, 0, 0.5) 85%, transparent 160px)">
      <br />
      <h4 style="color:white">{{room.title}}</h4>
      <h6>티어: {{room.tier}}</h6>
      <h6>참가자: 1/2</h6>
      </div>
      <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out white darken-2 v-card--reveal display-3 black--text"
            style="height: 15%;"
          >
            CLICK!
          </div>
        </v-expand-transition>
      </v-img>
      </v-card>
      </v-hover>

      <!-- <v-card id="d"  class="mx-auto" style="margin-top:10px" dark> 
      <v-img style="width:300px" src="https://ww.namu.la/s/6b4b67c5190df5f22ca4d4124e40901f80efa0e6bf5b9755c1daf5a6772a4664eed813cc049159c991c8981f46e728e5a2709f0fcaf05f1ee48f029d5d24eef98dcabc26a4f169a96b5c234b89c94fcabc634e7c2d853e91590f18235966cfda7763743d216e844a976501fed23e5daf"></v-img>
         <v-card-subtitle>
            <v-card-text>쌉캐리가능 미드구함
            <v-card-text>티어 : </v-card-text>
               <v-card-actions style="place-content:center" >
                  <v-btn color="blue-grey darken-2" @click="joinRank()">참여하기</v-btn>
               </v-card-actions >
            </v-card-text>
         </v-card-subtitle>
      </v-card>

      <v-card id="d" class="mx-auto" style="margin-top:10px" dark>
      <v-img style="width:300px" src="https://w.namu.la/s/b4fe89a2dc24d174ebba268d870c8672b0c40faaf4df07d0b36cc5ee8f5f40efe96007b2d3521aa6ca5805f4dce381f7914261627151c3627448d147982b3a87770cbdd59d21e76d2f64d040fcfe036f213102d46ef94824a269663dac73f262"></v-img>
         <v-card-subtitle>
            <v-card-text>쌉캐리가능 정글구함
            <v-card-text>티어 : </v-card-text>
               <v-card-actions style="place-content:center">
                  <v-btn color="blue-grey darken-2" @click="joinRank()">참여하기</v-btn>
               </v-card-actions>
            </v-card-text>
         </v-card-subtitle>
      </v-card>

      <v-card id="d"  class="mx-auto" style="margin-top:10px" dark> 
      <v-img style="width:300px" src="https://w.namu.la/s/0f03c257f252cb25791122b542f3c0b47d0d9c4631bc80d927f39836fefbc5f55247c28bda1ac910e6ffc2f278e3f198a1d940817bb752b1b80610703bb9511d250993cd3c998cb1699dab4535214332ac2cafb05f787bacf6d111bb8a94bb7cb5661acb2d409bd69cfa2e769b9d67ab"></v-img>
         <v-card-subtitle>
            <v-card-text>쌉캐리가능 미드구함
            <v-card-text>티어 : </v-card-text>
               <v-card-actions style="place-content:center" >
                  <v-btn color="blue-grey darken-2" @click="joinRank()">참여하기</v-btn>
               </v-card-actions >
            </v-card-text>
         </v-card-subtitle>
      </v-card>

      <v-card id="d" class="mx-auto" style="margin-top:10px" dark>
      <v-img style="width:300px" src="https://ww.namu.la/s/8b0a7e865d8841c425fc4f27b20c48281920bc574cffd4b9d014b867fdf443d391b841f307ca25fd2cf012758ef0ad9e312e3f12c929ba509a088075dcd0394ca839bf1a70fe53b0ff03e64eb409a1926a6710e30c532fc0e6c52f5a6e4909e5089bd055350211482a80fea3bdf8c6a7"></v-img>
         <v-card-subtitle>
            <v-card-text>쌉캐리가능 정글구함
            <v-card-text>티어 : </v-card-text>
               <v-card-actions style="place-content:center">
                  <v-btn color="blue-grey darken-2" @click="joinRank()">참여하기</v-btn>
               </v-card-actions>
            </v-card-text>
         </v-card-subtitle>
      </v-card> -->
      </v-row>
      </div>
</div>
</template>
<script>
import axios from 'axios'
export default {
   created(){
      let url = `http://localhost:8080/lol/list`
      let headers = {
              'authorization': 'JWT fefege..',
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
                }
      axios
           .get(url, headers)
           .then(res =>{
              this.rooms = res.data
              alert('리스트 가져오기 성공! ')
           })
           .catch(e=>{
              alert('AXIOS FAIL'+e)
           })
   },
   data(){
      return{
         row: 'rank',
         link: '/kalbaram',
         rooms:{
            title:'',
            tier:'',
            contents:'',
            rhost:'',
            imgurl:''
         }
      }
   },
   methods:{
      joinRank(){
         this.$router.push({path:'/joinRank'})
      },
      lol(){
         this.$router.push({path:'/lol'})
      },
      kal(){
         alert('칼바람은 준비중입니다.')
      },
      createRoom(){
         
         this.$router.push({path:'/createRoom'})
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