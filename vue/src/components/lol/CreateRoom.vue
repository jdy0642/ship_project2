<template>
<div id="app">
    <form class="ma-4">
    <v-card-title style="width:800px">
      <v-text-field
        label="카드 제목"
        v-model="title"
        :counter="30"
        placeholder="자신을 나타내는 강렬한 단어로 자신을 표현하세요! "
        required></v-text-field>
        </v-card-title>
      <v-card-text>
        <v-container fluid>
            <v-col center sm="1" md="6">
             <v-radio-group v-model="row" row>
        <v-radio label="개인/2인랭크게임" value="rank" ></v-radio>
        <!-- <v-radio label="칼바람나락" value="kal"></v-radio> -->
      </v-radio-group>
      <v-divider></v-divider>
              <v-radio-group v-model="ex8" column>
                <v-radio
                  label="브론즈"
                  color="#A1887F"
                  value="orange"
                ></v-radio>
                <v-radio
                  label="실버"
                  color="#CFD8DC"
                  value="grey"
                ></v-radio>
                <v-radio
                  label="골드"
                  color="#FFC107"
                  value="yellow"
                ></v-radio>
                <v-radio
                  label="플레티넘"
                  color="#A7FFEB"
                  value="info"
                ></v-radio>
                <v-radio
                  label="다이아"
                  color="#80D8FF"
                  value="tier2"
                ></v-radio>
                <v-radio
                  label="마스터"
                  color="#8C9EFF"
                  value="tier1"
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
          style="width:800px;"
          placeholder="내용을 입력해주세요"
        ></v-textarea>
    </form>
    <v-btn @click="createroom()">방생성</v-btn>
    <v-btn @click="lol()">방취소</v-btn>

</div>
</template>
<script>
import axios from 'axios'
import {store} from '@/store'
export default{
  data(){
    return{
      items: ['실버', '브론즈', '골드', '플레티넘'],
      row:'rank',
      userid : store.state.person.userid,
      tier: '',
      title: '',
      contents: ''

    }
  },
  methods:{
    lol(){
      this.$router.push({path:'/lol'})
    },
    createroom(){
      let url = `http://localhost:8080/createRoom`
           let headers = {
              'authorization': 'JWT fefege..',
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
                }
  
           let data = {
              userid : this.userid,
              tier : this.tier,
              title : this.title,
              contents : this.contents
              
           }
           axios
           .post(url, data, headers)
           .then(res =>{
              this.result = res.data
              alert(this.result)
           })
           .catch(e=>{
              alert('AXIOS FAIL'+e)
           })
    }
  }
}
</script>