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
      height:[30,5,7,50]
    }
  },
  created(){
    const rannum = () => ['4','5','6'][parseInt(Math.random()*3)]
    const rangender = () => ['female','male'][parseInt(Math.random()*2)]
		const ranrating = () => parseInt(Math.random()*3+1)
    const ranloc = () => ['신촌','강남','용인','종각','평양','부산','응암','보스턴'][parseInt(Math.random()*8)]
		const rantime = x => x + Math.random()*1000*3600*24*13
    const addr = () => ['어디어디어디 주소','거기어디어디 주소','어디어디거기 주소','어디어디 주소'][parseInt(Math.random()*4)]
		const ranfacility = () => 'size0,shower0,park0,shoes0,wear0'
		const remain = () => parseInt(Math.random()*12)
		let table = Array.from({length : 200},(_,i) => ({
			matchId: i, time: rantime(this.time), stadiumName: ranloc(),
      addr: addr(), num : rannum(), gender: rangender(), difficulty: ranrating(),
      shoes: 'shoes0', facility: ranfacility(),
			stadiumImg: '1,2,3', adminName: '펭수', remain: remain()
			}))
		//위쪽은 랜덤값. 실제는 axios로 받아와야함.
		table.map(x =>{
					x.groundSize = x.facility.split(",")[0]
					x.shower = x.facility.split(",")[1]
					x.parking = x.facility.split(",")[2]
					x.shoesRental = x.facility.split(",")[3]
					x.dressRental = x.facility.split(",")[4]
		})
		this.table = table
		store.state.matchList = table
	},
  computed: {
    matchFilter(){
      const time = this.time
      const stadiumName = this.stadiumName
      const table = this.table
      const utc = (x => (parseInt(x/3600/1000/24)*24 +
        (new Date(x).getHours() >= 9 ? 15 : 39))*3600*1000)
      return (stadiumName === '' ? table : table.filter(i=> i.stadiumName === stadiumName))
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
