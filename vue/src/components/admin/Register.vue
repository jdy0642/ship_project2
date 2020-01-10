<template>
<v-app>
<v-row style="margin:20px;float:left;">

<div class="card" style="text-align:left;width:700px;height:1050px">
  <div class="card-body">
    <h2 class="card-title" style="text-align:center">구장 등록</h2>
    <div style="margin:20px;padding:20px">
<div>
<form class="form-horizontal" action="" method="post" enctype="multipart/form-data">
<div class="form-group row">
<label class="col-md-3 col-form-label" for="text-input" style="">구장 이름</label>
<div class="col-md-9">
<input class="form-control" id="text-input" type="text" name="text-input" placeholder="신촌 1경기장">
</div>
</div>
<div class="form-group row">
<label class="col-md-3 col-form-label" for="text-input">관리자 이름</label>
<div class="col-md-9">
<input class="form-control" id="text-input" type="text" name="text-input" placeholder="이름">
</div>
</div>
<div class="form-group row">
<label class="col-md-3 col-form-label">관리자 연락처 </label>
<div class="col-md-9">
<input class="form-control" id="tel-input" type="tel" name="tel-input" placeholder="연락처 ">
</div>
</div>
<div class="form-group row">
<label class="col-md-3 col-form-label" for="email-input">관리자  Email </label>
<div class="col-md-9">
<input class="form-control" id="email-input" type="email" name="email-input" placeholder="Email" autocomplete="email">
</div>
</div>
<!-- <div class="form-group row">
<label @click="show()" type="date" class="col-md-3 col-form-label" for="date-input">경기 구장 등록 날짜 입력</label>

<div class="col-md-9">

<input class="form-control" id="date-input" type="date" name="date-input" placeholder="date">
</div>
</div> -->
<div class="form-group row">
<label class="col-md-3 col-form-label" for="disabled-input">경기 예약  비용</label>
<div class="col-md-9">
<input class="form-control" id="disabled-input" type="text" name="disabled-input" placeholder="10000원" disabled="">
</div>
</div>

<div class="form-group row">
<label class="col-md-3 col-form-label" for="select1">경기 인원 선택</label>
<div class="col-md-9">
<select class="form-control" id="select1" name="select1">
<option value="0">- - - - - - - - - -</option>
<option value="1">10명 </option>
<option value="2">12명</option>
<option value="3">14명</option>
<option value="4">16명</option>
</select>
</div>
</div>

<div class="form-group row">
<label class="col-md-3 col-form-label">등록 관리자 </label>
<div class="col-md-9 col-form-label">
<div class="form-check">
<input class="form-check-input" id="radio1" type="radio" value="radio1" name="radios">
<label class="form-check-label" for="radio1">Option 1</label>
</div>
<div class="form-check">
<input class="form-check-input" id="radio2" type="radio" value="radio2" name="radios">
<label class="form-check-label" for="radio2">Option 2</label>
</div>
<div class="form-check">
<input class="form-check-input" id="radio3" type="radio" value="radio2" name="radios">
<label class="form-check-label" for="radio3">Option 3</label>
</div>
</div>
</div>

<div class="form-group row">
<label class="col-md-3 col-form-label">Inline Checkboxes</label>
<div class="col-md-9 col-form-label">
<div class="form-check form-check-inline mr-1">
<input class="form-check-input" id="inline-checkbox1" type="checkbox" value="check1">
<label class="form-check-label" for="inline-checkbox1">One</label>
</div>
<div class="form-check form-check-inline mr-1">
<input class="form-check-input" id="inline-checkbox2" type="checkbox" value="check2">
<label class="form-check-label" for="inline-checkbox2">Two</label>
</div>
<div class="form-check form-check-inline mr-1">
<input class="form-check-input" id="inline-checkbox3" type="checkbox" value="check3">
<label class="form-check-label" for="inline-checkbox3">Three</label>
</div>
</div>
</div>
</form>
</div>
<div class="form-group row">
<label class="col-md-3 col-form-label" for="textarea-input">등록 시  특이사항</label>
<div class="col-md-9">
<textarea class="form-control" id="textarea-input" name="textarea-input" rows="5" placeholder="우천 시 경기 취소.. & 풋살화 공급 제한 & 최소 경기 인원 & 기타 등등..."></textarea>
</div>
</div>
</div>
<button class="btn btn-block btn-primary" style="width:50%;float : left; margin:0 auto" @click="register()">등록하기</button>
<button class="btn btn-block btn-warning" style="width:50%; float : right; margin:0 auto" @click="cancel()">취소</button>
</div>
</div>

<div class="card-body" width:autocomplete text-align="center" >
    <h3 class="card-title" >구장 등록 날짜 선택</h3>
 <v-date-picker 
  margin:auto
  v-model="picker"
  value
  left
  width="300"
  :allowed-dates="allowedDates"
  color = "blue"
  >
  <v-btn text color="primary" @click="adds()">해당 날짜 날씨 연동</v-btn></v-date-picker>
  
  <template>
<v-card
    width="300"
   left
   style="margin-top:20px;"
  color="#BBDEFB"
  >
    <v-list-item two-line>
      <v-list-item-content>
        <v-list-item-title class="headline">{{picker}} {{city}}</v-list-item-title>
        <v-list-item-subtitle >5 day / 3 hour forecast</v-list-item-subtitle>
      </v-list-item-content>
      <v-icon
        right
        color="blue"
        @click="refresh()"
        >refresh</v-icon>
    </v-list-item>
   <legend></legend>
  <div id="openweathermap-widget-24"></div>
    <v-card-text>
      <v-row align="center">
        <v-col class="display-3" cols="6">
          {{Math.ceil(temp-273)}}&deg;c
        </v-col>
        <v-col>
          <v-img
          :src="imgUrl"
          width="64"
          ></v-img>
        </v-col>
      </v-row>
    </v-card-text>
    <v-list-item>
      <v-img
      src="@/assets/wind.png"
      ></v-img><v-list-item-title>풍속: {{wind}}m/s</v-list-item-title>
    </v-list-item>
    <v-list-item>
      <v-img
      src="@/assets/humidity.png"
      ></v-img><v-list-item-title>습도: {{humidity}}%</v-list-item-title>
    </v-list-item>
    <v-list-item>
      <v-img
      src="@/assets/thermometer.png"
      ></v-img><v-list-item-title>최고온도: {{Math.ceil(maxtemp-273)}}&deg;c / 최저온도: {{Math.ceil(mintemp-273)}}&deg;c</v-list-item-title>
    </v-list-item>

    
    <v-slider
      v-model="time"
      :max="7"
      :tick-labels="labels"
      class="mx-4"
      ticks
      @click="timebars"
    ></v-slider>
    <!-- <v-list class="transparent">
      <v-list-item
        v-for="item in today"
        :key="item.dt"
        color="blue"
      >
        <v-list-item-title>{{ item.dt_txt }}</v-list-item-title>
        <v-list-item-icon>
          <v-icon>{{ item.weather[0].icon }}</v-icon>
        </v-list-item-icon>
        <v-list-item-subtitle class="text-right">
          {{ item.main.temp }}
        </v-list-item-subtitle>
      </v-list-item>
    </v-list> -->
  </v-card>
</template>
</div>
</v-row>
</v-app>
</template>
<script>
import axios from 'axios'
export default{
  created(){
      let url = `http://api.openweathermap.org/data/2.5/forecast?id=1835848&APPID=cd9a51369c3fc19f9fb85b2f2508b5d5`
      axios
      .get(url)
      .then(res=>{
        // for(let i=0;i<40;i+8){this.days = res.data.list.slice(i,40)}
        this.adata = res.data
        this.city = res.data.city.name
// -----------------------------------현재 시간 -------------------------------------------
        this.adds()
        
        // alert(this.today) // api에서 가지고 온 dt값을 대한민국 표준 시간으로 변환
// ------------------------------------------------------------------------------
      })
      .catch(e=>{
        alert('axios fail'+e)
        
      })
// -----------------------------------현재 시간 기반 데이터 가지고 오기 -------------------------------------------     
      // 현재 서울 날씨 API
      // let url = `http://api.openweathermap.org/data/2.5/weather?q=Seoul,KR&APPID=cd9a51369c3fc19f9fb85b2f2508b5d5`
      // let data = {
      //   city : this.city,
      //   temp : this.temp,
      //   img : this.temp,
      //   wind : this.wind
      // }
      // let headers= {
      //         'authorization': 'JWT fefege..',
      //         'Accept' : 'application/json',
      //         'Content-Type': 'application/json'
      //   }
     
      // axios
      // .get(url,data,headers)
      // .then(res=>{
      //   this.city = res.data.name
      //   this.temp = res.data.main.temp
      //   this.img = res.data.weather[0].icon
      //   this.imgUrl = `http://openweathermap.org/img/wn/${this.img}@2x.png`
      //   this.wind = res.data.wind.speed
      // })
      // .catch(e=>{
      //   alert('axios fail'+e)
        
      // })
// -----------------------------------데이터-------------------------------------------
      },
   data(){
      return{
      // date: new Date((this.days[0].dt+32400)*1000).toISOString().substr(0, 10),
      picker: this.$moment(new Date()).format('YYYY-MM-DD'),
      labels: [],
      // time:'',
      temp:'',
      mintemp:'',
      maxtemp:'',
      city:'',
      img:'',
      imgUrl:'',
      adata:[],
      wind:'',
      humidity:'',
      today:'',
      time:'',
      timebar:[]
      }
  },
// -----------------------------------메소-------------------------------------------
   methods:{
    allowedDates: val => parseInt(val.split('-')[2], 10) >= parseInt(new Date().toISOString().substr(0,10).split('-')[2], 10),
    show(x){
      this.temp=this.timebar[x].main.temp
      this.humidity = this.timebar[x].main.humidity
      this.maxtemp=this.timebar[x].main.temp_max
      this.mintemp=this.timebar[x].main.temp_min
      this.img = this.timebar[x].weather[0].icon
      this.wind = this.timebar[x].wind.speed
      this.imgUrl = `http://openweathermap.org/img/wn/${this.img}@2x.png`
    },
      register(){
         alert('등록 버튼 ')
    },
      cancel(){
         alert('취소 버튼 ')
    },
      refresh(){
         alert('초기화  버튼 ')
    },
    timebars(){
      this.show(this.time)
    },
    adds(){
      this.timebar = []
      // alert(`add메소드로 들어와서 계산된 값: ${parseInt(this.$moment(this.picker).format('D'))-parseInt(this.$moment(new Date()).format('D'))}`)
      switch (parseInt(this.$moment(this.picker).format('D'))-parseInt(this.$moment(new Date()).format('D'))) {
        case 0:
          for(let i=0; i<8;i++){
            this.labels.push(`${this.$moment(this.adata.list[i].dt*1000).format('H')}`)
            this.timebar.push(this.adata.list[i])
          }          
            this.show(0)
          break;
        case 1:
          for(let i=8; i<16;i++){
            this.labels.push(`${this.$moment(this.adata.list[i].dt*1000).format('H')}`)
            this.timebar.push(this.adata.list[i])
          }
            this.show(0)
          break;
        case 2:
          for(let i=16; i<24;i++){
            this.labels.push(`${this.$moment(this.adata.list[i].dt*1000).format('H')}`)
            this.timebar.push(this.adata.list[i])
          }
            this.show(0)
          break;
        case 3:
          for(let i=24; i<32;i++){
            this.labels.push(`${this.$moment(this.adata.list[i].dt*1000).format('H')}`)
            this.timebar.push(this.adata.list[i])
          }
            this.show(0)
          break;
        case 4:
          for(let i=32; i<40;i++){
          this.labels.push(`${this.$moment(this.adata.list[i].dt*1000).format('H')}`)
          this.timebar.push(this.adata.list[i])
          }
          this.show(0)
          break;
      }
    }
    
  },
  computed:{
    
  }
}
</script>
<style scoped>

</style>