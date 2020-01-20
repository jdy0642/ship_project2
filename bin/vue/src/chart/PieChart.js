import { Doughnut, mixins } from "vue-chartjs";
const { reactiveProp } = mixins
export default {
  extends: Doughnut,
  mixins: [reactiveProp],
  props: ["data", "options"],
  data(){
    return {
      chartdata:{
         datasets: [{
          data: []
        }],
        labels: [
        'Red',
        'Yellow',
        'Blue'
        ]
      }
    }
  },
  mounted() {
    // this.chartData is created in the mixin.
    // If you want to pass options please create a local options object
    this.renderChart(this.data, {
      borderWidth: "10px",
      hoverBackgroundColor: "red",
      hoverBorderWidth: "10px"
    });
  }
};