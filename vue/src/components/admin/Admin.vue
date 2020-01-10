<template>

  <div class="back">
     <div align = "center" ><h2> {{name}} 님의 어드민</h2>
     <h3> <a href="#" @click="logout">로그아웃  </a><a href="#" @click.prevent="withdrawal">회원탈퇴</a></h3>
     </div>
      <div class="sub">
         <div class="pull-mid size" >        
        <legend><h3>학생부 관리 </h3></legend>
        <input v-model="hak" type="text"  placeholder="변경할 학년 입력" >
        <input v-model="ban" type="text"  placeholder="변경할 반 입력" >
        <input v-model="score" type="text"  placeholder="변경할 점수 입력" >
        <br/><br/><button @click.prevent="update">수정</button><br/><br/>
		</div>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
import {store} from '../../store'
export default {
  name: 'admin',
  data(){
    return{
      context:'http://localhost:8080',
      name: store.state.name,
      person : store.state.person,
      hak:'',
	sidebars:[
        {menu: "비밀번호 수정", link:"/mypageupdate"}
      ],
      authCheck : true
    }
  },
  methods:{
    logout(){
      alert('로그아웃')
      alert(store.state.person.name)
      store.state.person={}
      alert(store.state.person.name)
      this.$router.push({path: '/login'})

    },
    withdrawal(){
      alert('회원탈퇴')
      axios
      .delete(`${this.context}/withdrawal/${store.state.person.userid}`)
      .then(
        alert('회탈 성공2')
      )
      .catch(()=>{
        alert('악시오스 회탈 실패')
      })
      this.$router.push({path:'/'})
    },
    update(){
      alert('비번 변경')
      let url = `${this.context}/update/${store.state.person.userid}`
      let data = {
        userid: store.state.person.userid,
        hak: store.state.person.hak,
        ban: store.state.person.ban,
        score: store.state.person.score
      }
      let headers = {
          'authorization': 'JWT fefege..',
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
      }
      axios
      .put(url, data, headers)
      .then(res=>{
        alert(res.data)
      })
      .catch(()=>{
        alert('악시오스 실패3')
      })
    }
  },
  created(){
	this.$router.push({path:'/studentlist'})
  }
}
</script>

 <style scoped>
    .back {width: 700px; margin: 0 auto;}
    .sub {width: 90%; height: 600px; margin: 0 auto; text-align: center;}
    .pull-left {float: left;}
    .pull-right {float: right;}
	.pull-mid{float: mid; margin: 50px 0px 0px 160px;}
    .top {margin-top: 1%;}
    .rt {margin-right: 1%;}
    .size {width: 49.5%; height: 100%;}
</style>
