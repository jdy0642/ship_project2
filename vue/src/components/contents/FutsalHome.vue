<template>
<div>
  <fut-head :style="`height: ${height[0]}vh`" :propImg="headImg" class="table"></fut-head>
  <search-bar :style="`height: ${height[1]}vh`" class="table" @send="setStadium"></search-bar>
  <reservation :style="`height: ${height[2]}vh`" class="table"  @send="setTime"></reservation>
  <reservation-table :style="`height: ${height[3]}vh`" ma-auto class="table"  
    :proptime="time" :propstadium="stadium"></reservation-table>
</div>
</template>

<script>
import FutHead from './futsal/FutHead'
import SearchBar from './futsal/SearchBar'
import Reservation from './futsal/Reservation'
import ReservationTable from './futsal/ReservationTable'
export default {
  components:{FutHead,SearchBar,Reservation,ReservationTable},
  data(){
    return{
      headImg: [
        'https://blog.hmgjournal.com/images/contents/article/20161214-Reissue-night-football-03.jpg',
        'http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042126_13.jpg',
        'http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042791_19.jpg'
      ],
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
