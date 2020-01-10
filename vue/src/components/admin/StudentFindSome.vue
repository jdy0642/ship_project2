<template>
	<div class="login-form">
    <form>
        <h2 class="text-center">조건 검색</h2>
        <a> 학년별 최상위, 최하위 학생 </a>       
        <div class="form-group">
            <input v-model="searchWord" type="text" class="form-control" placeholder="SearchWord" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" @click.prevent="find">전 송</button>
        </div>
        
		<a @click.prevent="namesOfStudents">1. 학생의 이름</a> <br />
		<a @click.prevent="streamToArray">2. 스트림을 배열로 전환</a><br />
		<a @click.prevent="streamToMap">3. 스트림을 맵으로 전환. 학생이름이  key</a><br />
		<a @click.prevent="theNumberOfStudents">4. 학생의 수</a><br/>
		<a @click.prevent="totalScore">5. 학생들 총점</a><br />
		<a @click.prevent="topStudent">6. 1등 학생</a><br />
		<a @click.prevent="getStat">7. 전체 학생 성적 통계</a><br />
		<a @click.prevent="nameList">8. 전체 학생 이름</a><br />
		<a @click.prevent="partioningBy">1.단순분할 (성별로 분할)</a><br />
		<a @click.prevent="partioningCountPerGender">2.단순분할 (성별 학생수)</a><br />
		<a @click.prevent="partioningTopPerGender">3.단순분할 (성별 1등)</a><br />
		<a @click.prevent="partioningRejectPerGender">4.다중분할 (성별 불합격자,  50점이하)</a><br />
		<a @click.prevent="groupingByBan">1. 단순 그룹화(반별로 그룹화)</a><br />
		<a @click.prevent="groupingByGrade">2. 단순그룹화(성적별로 그룹화)</a><br />
		<a @click.prevent="groupingByCountByLevel">3. 단순그룹화 + 통계(성적별 학생수))</a><br />
		<a @click.prevent="groupingByHakAndBan">4. 다중 그룹화 (학년별, 반별)</a><br />
		<a @click.prevent="groupingTopByHakAndBan">5. 다중 그룹화 +통계 (학년별, 반별  1등)</a><br />
		<a @click.prevent="groupingByStat">6. 다중그룹화 + 통계(학년별, 반별  성적그룹)</a>
		<a @click.prevent="groupingByHak">6. 학년별 그륩화</a>
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
      list:[],
      searchWord:''
		}
	},
	methods:{

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