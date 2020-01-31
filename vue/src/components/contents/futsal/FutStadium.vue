<template>
	<div>
		<fut-head :style="`height: ${height}vh`" :propImg="stadiumImg"/>
		<h1>{{stadiumName}}</h1>
		<fut-reservation-table v-for="(item,index) of timeArray" :key="index"
			:propStadium="stadiumName" :propTime="item" :propTable="fnc.matchFilter(item,stadiumName,table)"></fut-reservation-table>
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
			fnc: store.state.futsal.fnc,
			height: 30,
			selectMatch: store.state.futsal.selectMatch,
			table: store.state.futsal.matchList,
			stadiumName: store.state.futsal.selectMatch.stadiumname
		}
	},
	computed: {
		timeArray(){
			return Array.from({length: 7}, (_,k) =>
				(k!=0 ? this.fnc.utc(Date.now()) : Date.now()) + k*3600*1000*24)
		},
		stadiumImg(){
			return this.selectMatch.stadiumimg.split(",")
			.map(i => require(`@/assets/img/stadium/${i}.jpg`))
		}
	},
	methods: {
	}
}
</script>
<style scoped>
</style>