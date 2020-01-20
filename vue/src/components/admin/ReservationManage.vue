<template>
  <div style="margin:10px;">
  <div class="text-center" style="">
  </div>
   <v-card >
    <v-card-title style="padding-left:250px;color:black;background-color:#B0BEC5">
    <div><h2 style="font-weight:bold;">예약 현황</h2></div>
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="search"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      </v-card-title>
    <v-data-table
      :headers="headers"
      :items="lists"
      :search="search"
      :page.sync="page"
      :items-per-page="5"
      hide-default-footer
      class="elevation-1"
      @page-count="pageCount = $event"
    >
    <template>
    <v-data-table item-key="name" class="elevation-1" loading loading-text="Loading... Please wait"></v-data-table>
    </template> 
    </v-data-table>
    <div class="text-center pt-2">
      <v-pagination 
      prev-icon="mdi-arrow-left"
      next-icon="mdi-arrow-right" 
      circle
      color="grey"
      v-model="page" 
      :length="pageCount"></v-pagination>
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
    fixedHeader: true,
    page: 1,
    pageCount: 0,
    itemsPerPage: 5,
      value:1,
    lists: [],
    black:false,
    search: '',
        headers: [
          {
            text: '예약번호', align: 'left',
            filterable: false,
            value: 'resnum',
          },
          { text: '아이디', value: 'userid' },
          { text: '스타디움아이디', value: 'stadiumid' },
          { text: '예약일자', value: 'resdate' },
          
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