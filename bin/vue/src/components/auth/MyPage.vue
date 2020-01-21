<template>
<div style="height:860px;">
  <v-card>
    <v-toolbar flat>
      <v-toolbar-title class="title">MY Page</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">회원정보수정</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">회원정보</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="아이디" hint="변경불가" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="이름" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Email*" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field :rules="rules" counter="25" label="Password*" type="password" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-select
                  :items="['0-17', '18-29', '30-54', '54+']"
                  label="Age*"
                  required>
                </v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-autocomplete
                  :items="['여자', '남자']"
                  label="성별"
                  multiple>
                </v-autocomplete>
              </v-col>
            </v-row>
          </v-container>
          <small>수정 완료후 저장버튼을 눌러주세요.</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">닫기</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">저장</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-toolbar>
</v-card>
<v-card class="cardinfo" color="#26c6da"
        max-width="500"
        max-height="120">
        <v-card-title>
        <span class="title font-weight-light">최광진</span>
        <v-card-text class="headline font-weight-bold">MY POINT
        <span>10000원</span>
        <v-btn class="paybtn" rounded color="#ffc107" dark>충전하기</v-btn>
        <v-divider class="mx-4"></v-divider>
    </v-card-text>
    </v-card-title>
</v-card>
<v-container class="gameinfo">
    <v-expansion-panels
        v-for="(item,i) in 1"
        :key="i"
    >
      <v-expansion-panel>
        <v-expansion-panel-header>Match 1</v-expansion-panel-header>
        <v-expansion-panel-content>
    <v-row style="justify-content: center;">
          <v-card style="margin:20px;" width="100">
            <br>
          <p
      class="text-break"
      style="max-width: 3rem;"
    >
      12/12 토요일 15:00
    </p>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="220">
        <p></p>
        <v-text>부천 크라우드 76</v-text>
        <br>
        <v-text>남성매치</v-text>
        <v-text>(초급)</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>개인득점</v-text>
        <p></p>
        <v-text>2점</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>런닝</v-text>
        <p></p>
        <v-text>7KM</v-text>
    </v-card>
    </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header>Match 2</v-expansion-panel-header>
        <v-expansion-panel-content>
    <v-row style="justify-content: center;">
          <v-card style="margin:20px;" width="100">
            <br>
          <p
      class="text-break"
      style="max-width: 3rem;"
    >
      12/12 토요일 15:00
    </p>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="220">
        <p></p>
        <v-text>부천 크라우드 76</v-text>
        <br>
        <v-text>남성매치</v-text>
        <v-text>(초급)</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>개인득점</v-text>
        <p></p>
        <v-text>2점</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>런닝</v-text>
        <p></p>
        <v-text>7KM</v-text>
    </v-card>
    </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

            <v-expansion-panel>
        <v-expansion-panel-header>Match 3</v-expansion-panel-header>
        <v-expansion-panel-content>
    <v-row style="justify-content: center;">
          <v-card style="margin:20px;" width="100">
            <br>
          <p
      class="text-break"
      style="max-width: 3rem;"
    >
      12/12 토요일 15:00
    </p>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="220">
        <p></p>
        <v-text>부천 크라우드 76</v-text>
        <br>
        <v-text>남성매치</v-text>
        <v-text>(초급)</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>개인득점</v-text>
        <p></p>
        <v-text>2점</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>런닝</v-text>
        <p></p>
        <v-text>7KM</v-text>
    </v-card>
    </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

            <v-expansion-panel>
        <v-expansion-panel-header>Match 4</v-expansion-panel-header>
        <v-expansion-panel-content>
    <v-row style="justify-content: center;">
          <v-card style="margin:20px;" width="100">
            <br>
          <p
      class="text-break"
      style="max-width: 3rem;"
    >
      12/12 토요일 15:00
    </p>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="220">
        <p></p>
        <v-text>부천 크라우드 76</v-text>
        <br>
        <v-text>남성매치</v-text>
        <v-text>(초급)</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>개인득점</v-text>
        <p></p>
        <v-text>2점</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>런닝</v-text>
        <p></p>
        <v-text>7KM</v-text>
    </v-card>
    </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header>Match 5</v-expansion-panel-header>
        <v-expansion-panel-content>
    <v-row style="justify-content: center;">
          <v-card style="margin:20px;" width="100">
            <br>
          <p
      class="text-break"
      style="max-width: 3rem;"
    >
      12/12 토요일 15:00
    </p>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="220">
        <p></p>
        <v-text>부천 크라우드 76</v-text>
        <br>
        <v-text>남성매치</v-text>
        <v-text>(초급)</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>개인득점</v-text>
        <p></p>
        <v-text>2점</v-text>
    </v-card>
      <v-card style="margin:20px;" class="title font-weight-light" width="100">
        <p></p>
        <v-text>런닝</v-text>
        <p></p>
        <v-text>7KM</v-text>
    </v-card>
    </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
</v-container>
<div class="center">
  <div class="card">
    <div class="additional">
      <div class="user-card">
        <div class="level center">
          FutSal
        </div>
        <!-- 이미지 -->
        <img width="100px" height="100px" src="http://mblogthumb4.phinf.naver.net/MjAxODA0MTdfMjYx/MDAxNTIzOTIzOTU2MTE0.tKtnqOKqJas0q4h-RdyVZOQA0CfIisbvIdazrbBPB4kg.bO4IRWlDtIiZthVG0NmuyjK2TjkBAKprDUALHuZKv0Mg.PNG.gaogirl/%EC%B6%95%EA%B5%AC%EA%B3%B5.png?type=w800"
        style="margin-top: 90px;">
      </div>
      <div class="more-info">
        <h1>HITMAP</h1>
          <img width="300px" height="130px" src="http://tcpschool.com/lectures/img_webbasic_28.png" style="margin-top: 10px;">
        <div class="stats">
          <div>
            <div class="title">전적</div>
            <i class="fa fa-trophy"></i>
            <div class="value">5승</div>
          </div>
          <div>
            <div class="title">득점</div>
            <i class="fa fa-gamepad"></i>
            <div class="value">3점</div>
          </div>
          <div>
            <div class="title">런닝</div>
            <i class="fa fa-heartbeat"></i>
            <div class="value">25Km</div>
          </div>
          <div>
            <div class="title">경고</div>
            <i class="fa fa-hand-paper-o"></i>
            <div class="value infinity">3회</div>
          </div>
        </div>
      </div>
    </div>
      <img class="futsalimg" src="https://s3.eu-north-1.amazonaws.com/norkring/_articleLandscape/iStock-1149063259.jpg?mtime=20190925151000">
  </div>
  <div class="card green">
    <div class="additional">
      <div class="user-card">
        <div class="level center">
          League of Legends
        </div>
        <!-- 이미지 -->
        <img width="100px" height="100px" src="https://i.pinimg.com/originals/b8/3e/6f/b83e6fea403a390bd06ae17c187408e3.png"
        style="margin-top: 90px;">
      </div>
      <div class="more-info">
        <h1>Ranked</h1>
        <img width="200px" height="150px" src="https://opgg-static.akamaized.net/images/medals/challenger_1.png?image=q_auto&v=1" style="margin-left: 30px;">
        <div class="stats">
          <div>
            <div class="title">LP</div>
            <i class="fa fa-gamepad"></i>
            <div class="value">1,169</div>
          </div>
          <div>
            <div class="title">승</div>
            <i class="fa fa-trophy"></i>
            <div class="value">641</div>
          </div>
          <div>
            <div class="title">패</div>
            <i class="fa fa-thumbs-down"></i>
            <div class="value">621</div>
          </div>
          <div>
            <div class="title">승률</div>
            <i class="fa fa-bar-chart"></i>
            <div class="value infinity">51%</div>
          </div>
        </div>
      </div>
    </div>
    <img class="futsalimg" src="https://www.gamingdose.com/wp-content/uploads/2019/10/Qiyana-Louis-Vuitton-768x455.jpg">
  </div>
</div>
</div>
</template>
<script>
export default {
  data() {
    return {
      dialog : false,
      rules: [v => v.length <= 25 || 'Max 25 characters']
    }
  }
}
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css?family=Abel');
html, body {
  background: #FCEEB5;
  font-family: Abel, Arial, Verdana, sans-serif;
}
.center {
  position: absolute;
  top: 60%;
  left: 20%;
  -webkit-transform: translate(-50%, -50%);
}
.card {
  width: 500px;
  height: 300px;
  background-color: #fff;
  background: linear-gradient(#F8F8F8, #fff);
  box-shadow: 0 8px 16px -8px rgba(0,0,0,0.4);
  border-radius: 6px;
  overflow: hidden;
  position: relative;
  margin: 1.5rem;
}
.card h1 {
  text-align: center;
}
.card .additional {
  position: absolute;
  width: 150px;
  height: 100%;
  background: linear-gradient(#DE685E, #EE786E);
  transition: width 0.4s;
  overflow: hidden;
  z-index: 2;
}
.card.green .additional {
  background: linear-gradient(#92BCA6, #A2CCB6);
}
.card:hover .additional {
  width: 100%;
  border-radius: 0 5px 5px 0;
}
.card .additional .user-card {
  width: 150px;
  height: 100%;
  position: relative;
  float: left;
}
.card .additional .user-card::after {
  content: "";
  display: block;
  position: absolute;
  top: 10%;
  right: -2px;
  height: 80%;
  border-left: 2px solid rgba(0,0,0,0.025);
}
.card .additional .user-card .level,
.card .additional .user-card .points {
  left: 50%;
  top: 15%;
  color: #fff;
  text-transform: uppercase;
  font-size: 0.75em;
  font-weight: bold;
  background: rgba(0,0,0,0.15);
  padding: 0.125rem 0.75rem;
  border-radius: 100px;
  white-space: nowrap;
}
.card .additional .user-card .points {
  top: 85%;
}
.card .additional .more-info {
  width: 300px;
  float: left;
  position: absolute;
  left: 150px;
  height: 100%;
  font-size: 30px;
}
.card .additional .more-info h1 {
  color: #fff;
  margin-bottom: 0;
}
.card.green .additional .more-info h1 {
  color: #224C36;
}
.card .additional .coords {
  margin: 0 1rem;
  color: #fff;
  font-size: 1rem;
}
.card.green .additional .coords {
  color: #325C46;
}
.card .additional .coords span + span {
  float: right;
}
.card .additional .stats {
  font-size: 2rem;
  display: flex;
  position: absolute;
  bottom: 1rem;
  left: 1rem;
  right: 1rem;
  top: auto;
  color: #fff;
}
.card.green .additional .stats {
  color: #325C46;
}
.card .additional .stats > div {
  flex: 1;
  text-align: center;
}
.card .additional .stats i {
  display: block;
}
.card .additional .stats div.title {
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
}
.card .additional .stats div.value {
  font-size: 1.5rem;
  font-weight: bold;
  line-height: 1.5rem;
}
.card .additional .stats div.value.infinity {
  font-size: 1.4rem;
}
.card .general .more {
  position: absolute;
  bottom: 1rem;
  right: 1rem;
  font-size: 1em;
}
.cardinfo{
  margin-right: 60%;
  margin-top: 1%;
}
.gameinfo{
  width: 60%;
  margin-left: 40%;
}
.paybtn{
  float: right;
}
.futsalimg{
  width: 100%;
  height: 100%;
}
@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css);

</style>