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
     
      <v-radio-group style = "margin-left:40px" v-model="row" :mandatory="false">
        <v-radio label="개인/2인랭크" value="rank" ></v-radio>
        <v-radio style="margin-top:5px" @click="kal(link)" label="칼바람나락" value="radio-2"></v-radio>
      </v-radio-group>
    </v-container>
   <v-row>

      <v-card id="d" style = "margin-left:100px" >
         <v-img style="width:300px" src="https://w.namu.la/s/65e9c3777f6ccdb9e462635fd238f9bc30f2248bf4bff65178a31d8114f8f841fccdff26d0ef4b9a9f89698ab098c73fb54fb505b967ff6519dba358eabfdac15953d557f2dad09497236c50aaaf9f913b5feb26ef546432b8c3691bee906d5c6847f42160a7f7e9f4a7c3b621b88717"></v-img>
         <v-card-subtitle dark>
            <v-card-text>쌉캐리가능 정글구함
               <v-card-text>티어 : </v-card-text>
               <v-card-actions class="mx-auto" style="place-content:center">
                  <v-btn color="blue-grey darken-2" @click="joinRank()">참여하기</v-btn>
               </v-card-actions>
            </v-card-text>
         </v-card-subtitle>
      </v-card>

      <v-card id="d" class="mx-auto" dark> 
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

      <v-card id="d" style = "margin-right:100px" dark>
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

      <v-card id="d" style = "margin-left:250px;margin-top:50px" dark> 
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

   <v-spacer></v-spacer>
      <v-card id="d"   style = "margin-right:250px;margin-top:50px" dark> 
      <v-img style="width:300px" src="https://ww.namu.la/s/8b0a7e865d8841c425fc4f27b20c48281920bc574cffd4b9d014b867fdf443d391b841f307ca25fd2cf012758ef0ad9e312e3f12c929ba509a088075dcd0394ca839bf1a70fe53b0ff03e64eb409a1926a6710e30c532fc0e6c52f5a6e4909e5089bd055350211482a80fea3bdf8c6a7"></v-img>
         <v-card-subtitle>
            <v-card-text>쌉캐리가능 미드구함
            <v-card-text>티어 : </v-card-text>
               <v-card-actions style="place-content:center" >
                  <v-btn color="blue-grey darken-2" @click="joinRank()">참여하기</v-btn>
               </v-card-actions >
            </v-card-text>
         </v-card-subtitle>
      </v-card>
      
      </v-row>
      

</div>
</template>
<script>
export default {
   data(){
      return{
         row: 'rank',
         link: '/kalbaram'
      }
   },
   methods:{
      joinRank(){
         this.$router.push({path:'/joinRank'})
      },
      lol(){
         this.$router.push({path:'/lol'})
      },
      createRoom(){
         this.$router.push({path:'/createRoom'})
      },
      kal(x){
         this.$router.push({path:`${x}`})
      }
   }
}
</script>
<style scoped>
#d{
   height: 50%;
   
}

</style>