<template>
<div>
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
<div class="center">
  <div class="card">
    <div class="additional">
      <div class="user-card">
        <div class="level center">
          FutSal
        </div>
        <div class="points center">
          30,000 Points
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
    <div class="general">
      <h1>최근 경기</h1>
      <p>경기일자 : 2020-01-07</p>
      <p>경기장 : 부천경기장</p>
      <p>승,패 : 승리</p>
      <p>득점 : 1점</p>
      <p>경고 : 0회</p>
      <p>런닝 : 4Km</p>
      <p></p>
      <p></p>
      <span class="more">마우스를 올려보세요.</span>
    </div>
  </div>
  <div class="card green">
    <div class="additional">
      <div class="user-card">
        <div class="level center">
          League of Legends
        </div>
        <div class="points center">
          30,000 Points
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
    <div class="general">
      <h1>Hide on bush</h1>
      <div>선호하는 포지션
      </div>
      <div class="po">
      <img height="50px" width="50px" src="https://file.namu.moe/file/8ca81569f50919ce8c07b596e4a08b118704bb95be634079b2238663368897220aafdfe4b404a23c45a3dceb079a5463">
      <img height="50px" width="50px" src="https://w.namu.la/s/d417c241d97480f945f21476e56a337167e41bd0f19b56781ee5b4eb6c8225bccddcb64a308c85311b7937f202a9272834f581d08078cdf1c54d7ac05ba347c167b215ef430195b02b48f01b60c7e21800ea72b24d6bc946599f41078919a80f">
      </div>
      <div>최근 7일간 랭크 승률</div>
      <div class="chart">
        83%
        <span></span>
      </div>
      <span class="more">마우스를 올려보세요.</span>
    </div>
  </div>
</div>
<div style="margin-top: 650px;">
    <ul class="social-icons">
        <li><a href="#"><i class="fa fa-instagram"></i></a></li>
        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
        <li><a href="#"><i class="fa fa-comment-o"></i></a></li>
    </ul>
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
  top: 47%;
  left: 50%;
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
.card .general {
  width: 300px;
  height: 100%;
  position: absolute;
  top: 0;
  right: 0;
  z-index: 1;
  box-sizing: border-box;
  padding: 1rem;
  padding-top: 0;
}
.card .general .more {
  position: absolute;
  bottom: 1rem;
  right: 1rem;
  font-size: 1em;
}
@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css);
.social-icons {
  padding: 0;
  list-style: none;
  margin: 1em;
}
.social-icons li {
  display: inline-block;
  margin: 0.15em;
  position: relative;
  font-size: 1.2em;
}
.social-icons i {
  color: #fff;
  position: absolute;
  top: 21px;
  left: 21px;
  transition: all 265ms ease-out;
}
.social-icons a {
  display: inline-block;
}
.social-icons a:before {
  transform: scale(1);
  -ms-transform: scale(1);
  -webkit-transform: scale(1);
  content: " ";
  width: 60px;
  height: 60px;
  border-radius: 100%;
  display: block;
  background: linear-gradient(45deg, #FF003C, #C648C8);
  transition: all 265ms ease-out;
}
.social-icons a:hover:before {
  transform: scale(0);
  transition: all 265ms ease-in;
}
.social-icons a:hover i {
  transform: scale(2.2);
  -ms-transform: scale(2.2);
  -webkit-transform: scale(2.2);
  color: #FF003C;
  background: -webkit-linear-gradient(45deg, #FF003C, #C648C8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  transition: all 265ms ease-in;
}
.chart {
  margin: 22px auto;
  position: relative;
  width: 80px;
  height: 80px;
  border-radius: 50px;
  background: white;
  font: bold 35px/100px Sans-Serif;
  text-align: center;
  line-height: 80px;
  color: #54A2DD;
}
.chart:before {
  content: "";
  position: absolute;
  left: -20px;
  top: -20px;
  bottom: -20px;
  right: -20px;
  border-radius: 50%;
  background: #54A2DD;
  z-index: -2;
}
.chart span {
  position: absolute;
  width: 0;
  height: 0;
  border-left: 50px solid transparent;
  border-right: 50px solid transparent;
  border-top: 80px solid white;
  z-index: -1;
  top: -25px;
  left: 0px;
  transform-origin: 50px 80px;
  transform: rotate(285deg);
}
</style>