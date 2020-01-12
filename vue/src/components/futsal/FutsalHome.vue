<template>
<div>
  <fut-head :style="`height: ${height[0]}vh`"  class="table"></fut-head>
  <search-bar :style="`height: ${height[1]}vh`" class="table" @send="setStadium"></search-bar>
  <reservation :style="`height: ${height[2]}vh`" class="table"  @send="setTime"></reservation>
  <reservation-table :style="`height: ${height[3]}vh`" ma-auto class="table"  
    :proptime="time" :propstadium="stadium"></reservation-table>
</div>
</template>

<script>
import FutHead from './FutHead'
import SearchBar from './SearchBar'
import Reservation from './Reservation'
import ReservationTable from './ReservationTable'
export default {
  components:{FutHead,SearchBar,Reservation,ReservationTable},
  data(){
    return{
      stadium : '',
      time : Date.now(),
      height:[30,5,6,50]
    }
  },
  computed:{

  },
  methods:{
    setTime(time){
      this.time = time
    },
    setStadium(stadium){
      this.stadium = stadium
    }
  }
}
</script>
<style scoped>
.table{
	padding: 3px;
}
</style>
