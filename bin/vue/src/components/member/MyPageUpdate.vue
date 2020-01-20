<template>

  <div class="back">
     <div align = "center" ><h2> {{name}} 님의 My Page Update</h2>
     <h3> <a href="#" @click="logout">로그아웃  </a>
     <button type="button" class="btn btn-lg btn-danger"  @click.prevent="withdrawal">회원탈퇴</button>
     </h3>
     </div>
      <div class="sub">
        <div class="pull-left size" >
             <legend><h3>비밀번호 변경</h3></legend>
           <span><input v-model="passwd" type="text" name="newpwd" size="20" placeholder="변경할 비밀번호 입력" style="float: none;width: 70%;"></span>
                <button style="float: right;" @click="update">수정</button><br/><br/><br/>
                </div>
         <div class="pull-right size" >        
              <legend><h3>학생부 관리 </h3></legend>
                                학년:<input v-model="hak" type="text"  placeholder="변경할 학년 입력" > <br />
                                반:<input v-model="ban" type="text"  placeholder="변경할 반 입력">  <br />
                                점수:<input v-model="score" type="text"  placeholder="변경할 점수 입력" >
             <br/><br/><button @click.prevent="update">수정</button><br/><br/>
        </div>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
import {store} from '../../store'
export default {
  data(){
    return{
      context:'http://localhost:8080',
      id: store.state.person.id,
      userid: store.state.person.userid,
      passwd: store.state.person.passwd,
      name: store.state.person.name,
      birthday: store.state.person.birthday,
      gender: store.state.person.gender,
      hak:store.state.person.hak,
      ban:store.state.person.ban,
      score: store.state.person.score
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
  }
}
</script>

 <style scoped>
    .back {width: 700px; margin: 0 auto;}
    .sub {width: 90%; height: 600px; margin: 0 auto; text-align: center;}
    .pull-left {float: left;}
    .pull-right {float: right;}
    .top {margin-top: 1%;}
    .rt {margin-right: 1%;}
    .size {width: 49.5%; height: 100%;}
</style>
