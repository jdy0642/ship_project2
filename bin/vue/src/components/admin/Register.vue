<template>
<div>
<v-row style="margin:20px;float:left;text-align:center;">
<v-card style="text-align:center;width:700px;">
    <v-card-title>구장 등록</v-card-title>
    <div style="margin:20px;padding:20px">
<v-select
          :items="snames"
          label="구장을 선택해주세요."
          dense
          solo
        ></v-select>

          <v-text-field
            label="관리자 이름"
            outlined
            v-model="state.person.name"
          ></v-text-field>
          <v-text-field
            label="관리자 연락처"
            v-model="state.person.tel"
            outlined
          ></v-text-field>
          <v-text-field
            label="관리자 이메일"
            v-model="state.person.email"
            outlined
          ></v-text-field>
          <v-text-field
            label="구장 담당자 연락처"
            placeholder="Placeholder"
            outlined
          ></v-text-field>
          <v-combobox
          v-model="select"
          :items="items"
          label="경기장 옵션 선택"
          multiple
          chips
        ></v-combobox>
<template>
  <v-card float="center">
<label class="col-md-3 col-form-label" for="disabled-input">경기 예약  비용</label>
<v-radio-group  row v-model="radioGroup">
      <v-radio
        v-for="price of prices"
        :key="price"
        :label="`${price}`"
        :value="price"
        color="blue"
      ></v-radio>
    </v-radio-group>
</v-card>
</template>
<br />
<template>
  <v-card>
<label class="col-md-3 col-form-label" for="select1">경기 인원 선택</label>
<v-card-text>
      <v-slider
        v-model="fruits"
        :tick-labels="ticksLabels"
        :max="2"
        step="1"
        ticks="always"
        tick-size="3"
      ></v-slider>
    </v-card-text>
</v-card>
</template>
<br />
<v-textarea
          outlined
          name="input-7-4"
          label="구장 특이사항"
          value="우천 시 경기 취소.. & 풋살화 공급 제한 & 최소 경기 인원 & 기타 등등..."
        ></v-textarea>
</div>
</v-card>
<!-- -----------------------------------날짜 등록 ------------------------------------------- -->
<div class="card-body" text-align="center" >
    <h3 class="card-title" >등록 날짜 & 시간 연동</h3>
 <v-date-picker 
  margin:auto
  v-model="picker"
  value
  left
  width="300"
  :allowed-dates="allowedDates"
  color = "blue"
  
  >
  <v-btn text outlined color="primary" @click="adds()" bold>---------해당 날짜 날씨 연동---------</v-btn>
  </v-date-picker>
  <template>

<!-- -----------------------------------날씨------------------------------------------- -->
<v-card
    width="300"
   left
   style="margin-top:20px;"
  color="#303030"
  >
    <v-list-item two-line>
      <v-list-item-content>
        <v-list-item-title class="headline">{{picker}} {{city}}</v-list-item-title>
        <v-list-item-subtitle >5 day / 3 hour forecast</v-list-item-subtitle>
      </v-list-item-content>
      <v-icon
        right
        color="white"
        @click="refresh()"
        >refresh</v-icon>
    </v-list-item>
   <legend></legend>
  <div id="openweathermap-widget-24"></div>
    <v-card-text >
      <v-row align="center">
        <v-col class="display-3" cols="6">
          {{Math.ceil(temp-273.15)}}&deg;c
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
      <v-img style="color:white"
      src="@/assets/cloud.png"
      ></v-img><v-list-item-title>날씨: {{cloud}}</v-list-item-title>
    </v-list-item>
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
      ></v-img><v-list-item-title>최고온도: {{Math.ceil(maxtemp-273.15)}}&deg;c / 최저온도: {{Math.ceil(mintemp-273.15)}}&deg;c</v-list-item-title>
    </v-list-item>

<!-- -----------------------------------시간대 선택 ------------------------------------------- -->    
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
<br />
<button class="btn btn-block btn-primary" style="width:150px;height:60px; float : left; margin:0 auto" @click="register()">등록하기</button>
<button class="btn btn-block btn-warning" style="width:150px;height:60px; float : left; margin:0 auto" @click="cancel()">취소</button>
</div>
</v-row>
</div>
</template>
<script>
import {store} from '@/store'
import axios from 'axios'
export default{
  created(){
      let url = `http://api.openweathermap.org/data/2.5/forecast?id=1835848&APPID=cd9a51369c3fc19f9fb85b2f2508b5d5`
      axios
      .get(url)
      .then(res=>{
        this.adata = res.data
        this.city = this.adata.city.name
        this.adds()
      })
      .catch(e=>{
        alert('axios fail'+e)
        
      })
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
      timebar:[],
      cloud:'',
      state:store.state,
      radioGroup: 1,
      select: [],
        items: [
          '풋살화 대여 가능',
          '운동복 대여 가능',
          '주차 가능',
          '샤워장 이용 가능',
        ],
      snames:[
        '서울 경기장',
        '부산 경기장',
        '인천 경기장',
        '강원 경기장 '
      ],
      value: 0,
        fruits: 0,
        ticksLabels: [
          '4 vs 4',
          '5 vs 5',
          '6 vs 6',
        ],
      prices:[
        '10000원',
        '12000원',
        '15000원',
        '18000원',
        '20000원',
      ]
      }
  },
// -----------------------------------메소드-------------------------------------------
   methods:{
    allowedDates: val => parseInt(val.split('-')[2], 10) >= parseInt(new Date().toISOString().substr(0,10).split('-')[2], 10),
    show(x){
      this.temp=this.timebar[x].main.temp
      this.humidity = this.timebar[x].main.humidity
      this.maxtemp=this.timebar[x].main.temp_max
      this.mintemp=this.timebar[x].main.temp_min
      this.img = this.timebar[x].weather[0].icon
      this.wind = this.timebar[x].wind.speed
      if(parseInt(this.timebar[x].clouds.all)<25) {
        this.cloud = '맑음'
      }else if(parseInt(this.timebar[x].clouds.all)<45){
        this.cloud = '구름 조금'
      }else if(parseInt(this.timebar[x].clouds.all)<65){
        this.cloud = '흐림'
      }else{
        this.cloud = '매우 흐림'
      }
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