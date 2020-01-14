<template>
	<div>
		<fut-head :style="`height: ${height}vh`" :propImg="stadiumImg"/>
		<h1>{{stadium}}</h1>
		<reservation-table v-for="(item,index) of timeArray" :key="index"
			:propstadium="selectMatch.stadiumName" :proptime="item"></reservation-table>
	</div>
</template>
<script>
import ReservationTable from './ReservationTable'
import FutHead from './FutHead'
import {store} from '@/store'
export default{
	components:{FutHead,ReservationTable},
	data(){
		return {
			height: 30,
			selectMatch: store.state.selectMatch
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
			return this.selectMatch.stadiumImg.split(",")
			.map(i => require(`@/assets/img/stadium/${i}.jpg`))
		},
	}
}
</script>
<style scoped>
</style>