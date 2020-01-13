<template>
<div style="padding:10px;">
<div class="small" style="margin:10px;width:800px;">
  <v-card style="float:left">
  <v-card-title>수익분석 페이지</v-card-title>
  <v-card-subtitle>담당 구장 수익 분석 페이지</v-card-subtitle>
  <v-col cols="12" >
      <v-hover v-slot:default="{ hover }">
        <v-card
          :elevation="hover ? 12 : 2"
          class="mx-auto"
          height="400"
          width="800px"
          style="float:right"
        >
        <line-chart :chart-data="datacollection"></line-chart>
        </v-card>
      </v-hover>
  </v-col>
  </v-card>
</div>
<div class="small" style="width:800px;">
  <v-col cols="12">
      <v-hover v-slot:default="{ hover }">
        <v-card
          :elevation="hover ? 12 : 2"
          class="mx-auto"
          height="400"
          max-width="350"
          style="float:left"
        >
        <v-card-title></v-card-title>
        <bar-chart :chartdata="datacollection"></bar-chart>
        </v-card>
        </v-hover>
        <br />
        <v-hover v-slot:default="{ hover }">
        <v-card
          :elevation="hover ? 12 : 2"
          class="mx-auto"
          height="400"
          max-width="350"
          style="float:right"
        >
        <bar-chart :chart-data="datacollection"></bar-chart>
        </v-card>
        </v-hover>
    </v-col>
</div>
  <v-btn @click="fillData()" color:blue>요일 별 남녀 구매차트</v-btn>
</div>
</template>

<script>
  import BarChart from '@/chart/BarChart.js'
  import LineChart from '@/chart/LineChart.js'
  export default {
    components: {
      BarChart,
      LineChart
    },
    data () {
      return {
        datacollection: null
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
              label: '여성',
              backgroundColor: '#D32F2F',
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }, {
              label: '남성',
              backgroundColor: '#1976D2',
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
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