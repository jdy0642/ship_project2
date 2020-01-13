import { Bar,mixins } from 'vue-chartjs'
const { reactiveProp } = mixins
export default {
  extends: Bar,
  mixins: [reactiveProp],
  data(){
    return{
    chartdata: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      datasets: [
        {
          label: 'data one',
          backgroundColor: '#f87979',
          data: [40, 20,30,15,20,20,35,20,35,30,25,40]
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false
    }
  }
},

  mounted () {
    this.renderChart(this.chartdata, this.options)
  }
}