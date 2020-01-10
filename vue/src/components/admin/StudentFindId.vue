<template>
	<div class="login-form">
    <form>
        <h2 class="text-center">학번 검색</h2>       
        <div class="form-group">
            <input v-model="searchWord" type="text" class="form-control" placeholder="SearchWord" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" @click.prevent="find">전 송</button>
        </div>
    </form>
  </div>
  <!-- <div>
  <h1> 학생 목록
  <br />
  <br />
  <table class="table">
    <tr>
      <th>No.</th> 
      <th>아이디</th>
      <th>비밀번호</th>
      <th>이름</th>
      <th>생년월일</th>
      <th>학년</th>
      <th>반</th>
      <th>점수 </th>
      <th>남녀 </th>
      <th>역할 </th>
    </tr>
    <tr v-for="(j, i) of list" :key="j.id">
      <td>{{i+1}}</td> 
      <td>{{j.userid}}</td>
      <td>{{j.passwd}}</td>
      <td>{{j.name}}</td>
      <td>{{j.birthday}}</td>
      <td>{{j.hak}}</td>
      <td>{{j.ban}}</td>
      <td>{{j.score}}</td>
      <td>{{j.gender}}</td>
      <td>{{j.role}}</td>
    </tr>
  </table>
  </h1>
</div> -->
</template>
<script>
export default {
	data () {
		return {
      context:'http://localhost:8080',
      list:[]
		}
	}
}
</script>
<style scoped>
.login-form {
	width: 340px;
    margin: 50px auto;
	}
    .login-form form {
    margin-bottom: 15px;
    background: #f7f7f7;
    box-shadow:  2px 2px 2px 2px rgba(0, 0, 0, 0.5);
    padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>