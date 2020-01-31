<template>
  <div style="margin:10px;">
  <div class="text-center" style="">
  </div>
   <v-card >
    <v-card-title style="padding-left:250px;color:black;background-color:#B0BEC5">
    <div><h2 style="font-weight:bold;">회원 목록 관리</h2></div>
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
    <template v-slot:item.male="{item}">
				<p>
					{{String(item.male) == 'true' ? '남성' : '여성'}}
				</p>
			</template>
      <template v-slot:item.lolblack="{item}">
				<v-switch
      v-model="black"
      :label="`${item.lolblack}`"
    ></v-switch>
			</template>
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
         .get(`/customermanage`)
         .then(res =>{
            this.lists = res.data
            for(let i=0; i<this.lists.length;i++){
              this.gender.push(this.lists[i].male)
            }

         })
         .catch(e=>{
            alert('AXIOS FAIL'+e)
         })
  },
   data(){
      return{
        gender:[],
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
            text: '고유번호',
            align: 'left',
            filterable: false,
            value: 'personseq',
          },
          { text: '아이디', value: 'userid' },
          { text: '비밀번호', value: 'passwd' },
          { text: '이름', value: 'name' },
          { text: '연락처', value: 'tel' },
          { text: '포인트', value: 'point' },
          { text: '나이', value: 'age' },
          { text: '성별', value: 'male'},
          { text: '관심사', value: 'interest' },
          { text: '이메일', value: 'email' },
          { text: '롤블랙', value: 'lolblack' },
          { text: '구분', value: 'job' },
          
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