<template>
  <div style="margin:10px;">
  <div class="text-center" style="">
  </div>
   <v-card >
    <v-card-title style="padding-left:250px;color:black;background-color:#B0BEC5">
      <div><h2 style="font-weight:bold;">예약 현황</h2></div>
        <v-spacer></v-spacer>
        <v-text-field single-line v-model="search" append-icon="search" label="조건검색"></v-text-field>
    </v-card-title>
      <v-data-table :headers="headers" :items="lists" :search="search" :page.sync="page"
        :items-per-page="5" hide-default-footer @page-count="pageCount = $event" style="margin-top:15px;text-align-last:center">
        <template v-slot:item.resdate="{item}">
          {{fnc.timeToDate(item.resdate)}}
        </template>
        <template v-slot:item.personseq.userid="{item}">
          <v-btn @click="setMatchResult(item.futsalmatchseq.futsalmatchseq)">{{item.personseq.userid}}</v-btn>
        </template>
      </v-data-table>
      <div class="text-center pt-2">
        <v-pagination prev-icon="mdi-arrow-left" next-icon="mdi-arrow-right" circle
                      color="grey" v-model="page" :length="pageCount" style="margin-bottom:15px;"></v-pagination>
      </div>
    </v-card>
  </div>
</template>
<script>
import axios from 'axios'
import { store } from '@/store'
export default {
  created(){
    axios
         .get(`/res/1`)
         .then(res =>{
            this.lists = res.data.sort((a,b) =>
              a.resdate > b.resdate ? 1 : (a.resdate < b.resdate ? -1 : 0))
         })
         .catch(e=>{
            alert('AXIOS FAIL'+e)
         })
  },
   data(){
      return{
    fnc: store.state.futsal.fnc,
    page: 1,
    pageCount: 0,
    itemsPerPage: 20,
    lists: [],
    black:false,
    search: '',
    headers: [
          { text: '예약 번호', value: 'resseq'},
          { text: '예약 일자', value: 'resdate' },
          { text: '구장명', value: 'futsalmatchseq.stadiumname'},
          { text: '유저 아이디', value: 'personseq.userid' },
        ],
      }
   },
   methods:{
    blacklist(){
      alert('블랙 리스트 관리 창으로 이동  ')
    },
    member(x){
      alert(x+'님 개인 설정')
    },
    goto(){
      alert('search')
    },
    setMatchResult(personseq){
      alert(personseq)
      //this.search = event.currentTarget.firstChild.nodeValue
    }
   }
  }
</script>
<style scoped></style>