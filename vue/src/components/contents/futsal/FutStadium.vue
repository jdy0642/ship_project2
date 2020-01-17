<template>
	<div>
		<fut-head :style="`height: ${height}vh`" :propImg="stadiumImg"/>
		<h1>{{stadiumName}}</h1>
		<fut-reservation-table v-for="(item,index) of timeArray" :key="index"
			:propStadium="stadiumName" :propTime="item" :propTable="matchFilter(item)"></fut-reservation-table>
	</div>
</template>
<script>
import FutReservationTable from './FutReservationTable'
import FutHead from './FutHead'
import {store} from '@/store'
export default{
	components:{FutHead,FutReservationTable},
	data(){
		return {
			height: 30,
			selectMatch: store.state.selectMatch,
			table: store.state.matchList,
			stadiumName: store.state.selectMatch.stadiumname
		}
	},
	computed: {
		timeArray(){
			const utc = (x => (parseInt(x/3600/1000/24)*24 +
				(new Date(x).getHours() >= 9 ? -9 : 15))*3600*1000)
			return Array.from({length: 7}, (_,k) =>
				(k!=0 ? utc(Date.now()) : Date.now()) + k*3600*1000*24)
		},
		stadiumImg(){
			return this.selectMatch.stadiumimg.split(",")
			.map(i => require(`@/assets/img/stadium/${i}.jpg`))
		}
	},
	methods: {
		matchFilter(time){
			const stadiumName = this.stadiumName
			const table = this.table
			const utc = (x => (parseInt(x/3600/1000/24)*24 +
				(new Date(x).getHours() >= 9 ? 15 : 39))*3600*1000)
			return table
				.filter(i => i.stadiumname === stadiumName)
				.filter(i => time <= i.time && i.time < utc(time))
				.sort((a,b) => a.time > b.time ? 1 : (a.time < b.time ? -1 : 0))
		}
	}
}
</script>
<style scoped>
</style>