<template>
<div id="app" style="height:100%">
    <form class="ma-4">
    <v-card-title style="width:600px">
      <v-text-field
        label="카드 제목"
        v-model="title"
        :counter="20"
        placeholder="자신을 나타내는 강렬한 단어로 자신을 표현하세요! "
        required></v-text-field>
        </v-card-title>
      <v-card-text>
        <v-container fluid>
            <v-col center sm="1" md="6">
             <v-radio-group v-model="row" row>
        <v-radio label="개인/2인랭크게임" value="rank" ></v-radio>
        <v-radio label="칼바람(준비중 )" ></v-radio>
        <v-radio label="URF(준비중 )" ></v-radio>
        <v-radio label="롤토체스(준비중 )" ></v-radio>
        <!-- <v-radio label="칼바람나락" value="kal"></v-radio> -->
      </v-radio-group>
      <v-divider></v-divider>
              <v-radio-group v-model="tier" column>
              <v-radio
                  label="아이언"
                  color="#6D4C41"
                  value="iron"
                ></v-radio>
                <v-radio
                  label="브론즈"
                  color="#6D4C41"
                  value="bronze"
                ></v-radio>
                <v-radio
                  label="실버"
                  color="#CFD8DC"
                  value="silver"
                ></v-radio>
                <v-radio
                  label="골드"
                  color="#FF9800"
                  value="gold"
                ></v-radio>
                <v-radio
                  label="플레티넘"
                  color="#00796B"
                  value="platinum"
                ></v-radio>
                <v-radio
                  label="다이아"
                  color="#0288D1"
                  value="diamond"
                ></v-radio>
                <v-radio
                  label="마스터"
                  color="#4DB6AC"
                  value="master"
                ></v-radio>
                <v-radio
                  label="그랜드 마스터"
                  color="#4DB6AC"
                  value="grande master"
                ></v-radio>
                <v-radio
                  label="챌린져"
                  color="#AFB42B"
                  value="challenger"
                ></v-radio>
                
              </v-radio-group>
              
            </v-col>
        </v-container>
      </v-card-text>
      <!-- <textarea v-model="contents" outlined="outlined" placeholder="컨텐츠 내용 입력" background-color="grey lighten-2" id="comment" name="a1670651c2" cols="130" rows="6" maxlength="65525" aria-required="true" required="required"></textarea> -->
      <!-- <v-textarea
      background-color="grey lighten-2"
      filled
      placeholder="내용을 입력해주세요"
      color="black"
      outlined
      auto-grow
      row-height="30"
      style="width:800px;height:400px;color:black"
    ></v-textarea> -->
    <v-textarea
          v-model="contents"
          outlined
          name="input-7-4"
          label="내용"
          row-height="30"
          auto-grow
          style="width:600px;"
          placeholder="내용을 입력해주세요"
          :counter="50"
        ></v-textarea>
    </form>
    <div style="padding:10px">
    <v-btn style="width:150px" color="success" @click="crawling()">방 생성하기</v-btn>
    <v-btn style="width:150px" color="error" @click="lol()">취소하고 돌아가기</v-btn>
    </div>
</div>
</template>
<script>
import axios from 'axios'
import {store} from '@/store'
export default{
  data(){
    return{
      row:'rank',
      tier: '',
      title: '',
      contents: '',
      state : store.state,
      temp : '',
      result : {},
      crawltier:'',
      crawlrate:'',
      img:'',
      wtime: this.$moment(new Date())
    }
  },
  methods:{
    lol(){
      this.$router.push({path:'/lol'})
    },
    crawling(){
    let url = `/lol/summoner/userName=${this.state.person.summonername}`
    axios
    .get(url)
    .then(res=>{
      this.temp = res.data[0]
      this.crawltier = this.temp.tier
      this.crawlrate = this.temp.rate
      this.img = this.temp.photo
      this.createroom()
    })
    .catch(e=>{
      alert('axios fail'+e)
    })
    },
    createroom(){
      // this.crawling()
      let url = `/lol/createroom`
           let headers = {
              'authorization': 'JWT fefege..',
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
                }
  
           let data = {
              rhost : this.state.person.summonername,
              tier : this.tier,
              title : this.title,
              contents : this.contents,
              crawltier : this.crawltier,
              crawlrate : this.crawlrate,
              img : this.img,
              wtime : this.wtime
           }
           axios
           .post(url, data, headers)
           .then(res =>{
              this.result = res.data
              // alert('방생성 완료!')
              this.$router.push({path:`/lol`})
           })
           .catch(e=>{
              alert('AXIOS FAIL'+e)
           })
    }
  }
}
</script>