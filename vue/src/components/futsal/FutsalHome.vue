<template>
<div style="display: grid; grid-template-rows: 35% 5% 7% 50%; border: solid 1px; height: 100vh;">
  <Header></Header>
  <searchBar class="table" @send="setStadium"></searchBar>
  <reservation class="table"  @send="setTime"></reservation>
  <reservationTable class="table"  :proptime="time" :propstadium="stadium" ></reservationTable>
</div>
</template>

<script>
import Header from './Header'
import SearchBar from './SearchBar'
import Reservation from './Reservation'
import ReservationTable from './ReservationTable'
export default {
  components:{Header,SearchBar,Reservation,ReservationTable},
  data(){
    return{
      stadium : '',
      time : Date.now()
    }
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
