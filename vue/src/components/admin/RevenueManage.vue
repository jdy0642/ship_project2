<template>
<div style="padding:10px;">
  <v-card style="float:left;">
  <v-card-title>수익분석 페이지 <v-btn @click="fillData()" color:blue>요일 별 남녀 구매차트</v-btn></v-card-title>
  <v-card-subtitle>담당 구장 수익 분석 페이지</v-card-subtitle>
  
      <v-hover v-slot:default="{ hover }">
        <v-card
          :elevation="hover ? 12 : 2"
          class="mx-auto"
          style="float:right"
          width="1100px"
        >
        <bar-chart :chart-data="datacollection"></bar-chart>
        
        </v-card>
        
      </v-hover>
        <br /><br />
        <v-row>
        <v-hover v-slot:default="{ hover }">
        <v-card
          :elevation="hover ? 12 : 2"
          class="mx-auto"
          height="450"
          max-width="350"
          style="float:left"
        >
        <line-chart :chart-data="ager"></line-chart>
        </v-card>
        </v-hover>
  <v-hover v-slot:default="{ hover }">
        <v-card
          :elevation="hover ? 12 : 2"
          class="mx-auto"
          height="450"
          max-width="350"
          style="float:left"
        >
  <pie-chart :chart-data="gender"></pie-chart>
  </v-card>
        </v-hover>
        </v-row>
  </v-card>
    
</div>
</template>

<script>
  import BarChart from '@/chart/BarChart.js'
  import LineChart from '@/chart/LineChart.js'
  import PieChart from '@/chart/PieChart.js'
  export default {
    components: {
      BarChart,
      LineChart,
      PieChart
    },
    data () {
      return {
        datacollection: null,
        gender:null,
        ager:null
      }
    },
    mounted () {
      this.fillData()
    },
    methods: {
      fillData () {
        this.datacollection = {
          labels: ['월요일', '화요일','수요일','목요일', '금요일', '토요일', '일요일'],
          datasets: [
            {
              label: '서울 경기장',
              backgroundColor: '#FF1744',
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }, {
              label: '인천 경기장',
              backgroundColor: '#FF6D00',
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }, {
              label: '강원  경기장',
              backgroundColor: '#FFD600',
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }, {
              label: '수원 경기장',
              backgroundColor: '#64DD17',
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }, {
              label: '부산 경기장',
              backgroundColor: '#0091EA',
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }
          ]
        },
        this.gender = {
          labels:['남성 ', '여성'],
          datasets: [
            {
              label: '여성',
              backgroundColor: ['#4DD0E1','#FF4081'],
              data: [this.getRandomInt(), this.getRandomInt()]
            }]
        },
        this.ager={
          labels:['10대 ', '20대', '30대', '40대'],
          datasets: [
            {
              label: '예약현황',
              backgroundColor: ['#EA80FC'],
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }
          ]
        }
      },
      getRandomInt () {
        return Math.floor(Math.random() * (50 - 5 + 1))
      }
    }
  }
</script>

<style>
  .small {
  }
</style>