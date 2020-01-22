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
export default {
  created(){
    axios
         .get(`${this.context}/res/1`)
         .then(res =>{
            this.lists = res.data
         })
         .catch(e=>{
            alert('AXIOS FAIL'+e)
         })
  },
   data(){
      return{
    context : 'http://localhost:8080',
    page: 1,
    pageCount: 0,
    itemsPerPage: 5,
    lists: [],
    black:false,
    search: '',
    headers: [
          { text: '예약 번호', value: 'resnum'},
          { text: '예약 일자', value: 'resdate' },
          { text: '구장명', value: 'stadiumid'},
          { text: '유저 아이디', value: 'userid' },
          { text: '시퀀스 ', value:'resseq'}
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
    }

   }
  }
</script>
<style scoped></style>