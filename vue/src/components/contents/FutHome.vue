<template>
<div>
  <fut-head :style="`height: ${height[0]}vh`" :propImg="headImg" class="table"></fut-head>
  <fut-search-bar :style="`height: ${height[1]}vh`" class="table" @send="setStadium"></fut-search-bar>
  <fut-reservation :style="`height: ${height[2]}vh`" class="table"  @send="setTime"></fut-reservation>
  <fut-reservation-table :style="`height: ${height[3]}vh`" ma-auto class="table"  
    :propTime="time" :propStadium="stadiumName" :propTable="matchFilter"></fut-reservation-table>
</div>
</template>

<script>
import FutHead from './futsal/FutHead'
import FutSearchBar from './futsal/FutSearchBar'
import FutReservation from './futsal/FutReservation'
import FutReservationTable from './futsal/FutReservationTable'
import {store} from '@/store'
import axios from 'axios'
export default {
  components:{FutHead,FutSearchBar,FutReservation,FutReservationTable},
  data(){
    return{
      headImg: [
        'https://blog.hmgjournal.com/images/contents/article/20161214-Reissue-night-football-03.jpg',
        'http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042126_13.jpg',
        'http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042791_19.jpg'
      ],
      stadiumName : '',
      time : Date.now(),
      table : [],
      height:[30,5,7,50],
      getdata:'',
    }
  },
  created(){
    let table = []
    axios.get(`${store.state.context}/futsal/`)
      .then(res => {
        this.getdata = res.data
        table = res.data
        table.map(x =>{
					x.stadiumGroundSize = x.stadiumfacility.split(',')[0]
					x.stadiumShower = x.stadiumfacility.split(',')[1]
					x.stadiumParking = x.stadiumfacility.split(',')[2]
					x.stadiumShoesRental = x.stadiumfacility.split(',')[3]
          x.stadiumDressRental = x.stadiumfacility.split(',')[4]
        })
        this.table = table
        store.state.matchList = table	
    }).catch(e => {
        alert(`axios fail ${e}`)
    })
	},
  computed: {
    matchFilter(){
      const time = this.time
      const stadiumName = this.stadiumName
      const table = this.table
      const utc = (x => (parseInt(x/3600/1000/24)*24 +
        (new Date(x).getHours() >= 9 ? 15 : 39))*3600*1000)
      return (stadiumName === '' ? table : table.filter(i=> i.stadiumname === stadiumName))
        .filter(i => time <= i.time && i.time < utc(time))
        .sort((a,b) => a.time > b.time ? 1 : (a.time < b.time ? -1 : 0))
    }  
  },
  methods: {
    setTime(time){
      this.time = time
    },
    setStadium(stadiumName){
      this.stadiumName = stadiumName
    }
  }
}
</script>
<style scoped>
.table{
	padding: 3px;
}
</style>
