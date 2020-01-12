<template>
<div class="ma-auto">
	{{timeToDate(proptime)}}  {{propstadium}}
	<!-- <div v-for="(match,index) of matchFilter(propstadium,proptime)" :key="index"
	style="display: grid; grid-template-columns: 10% 20% 45% 25%; border: solid 1px;">
		<div v-for="(item,key) of match" :key="key" @click="click(item,key)" style="text-align: center">
			{{key!='time' ? item : timeToDate(item)}}
		</div>
	</div> -->
	<v-card raised ma-auto>
		<v-data-table
		:headers="headers"
		:items="matchFilter(propstadium,proptime)"
		class="elevation-1" hide-default-footer>
		<template v-slot:item.time="{item}">
			{{timeToDate(item.time)}}
			<!-- <v-btn @click="click(item)">{{timeToDate(item.time)}}</v-btn> -->
		</template>
		<template v-slot:item.id="{item}">
			<v-btn @click="click()">{{item.id}}</v-btn>
		</template>
		</v-data-table>
	</v-card>
</div>
</template>
<script>
import {store} from '@/store'
export default({
	props: {
		proptime: {type: Number},
		propstadium: {type: String}
	},
	data(){
		return{
			headers: [
				{ text: '경기날짜', width: 200, value: 'time' },
				{ text: '경기장', value: 'stadium' },
				{ text: '인원', value: 'num' },
				{ text: '성별', value: 'gender' },
				{text: '경기 번호', align: 'center',sortable: false,value: 'id'}
			],
			table: []
		}
	},
	created(){
		const ranloc = () => ['신촌','강남','용인','종각','평양','부산','응암','보스턴'][parseInt(Math.random()*8)]
		const rantime = x => x + Math.random()*1000*3600*24*13
		const rannum = () => parseInt(Math.random()*3+4)
		const gender = () => parseInt(Math.random()*2)
		const level = () => parseInt(Math.random()*3+1)
		this.table = Array.from({length : 200},(_,i) =>
			({id: i, time: rantime(this.proptime), stadium: ranloc(), num: rannum(), gender: gender(), level: level()}))
		store.state.matchList = this.table
	},
	methods:{
		timeToDate(time){
			const temp = new Date(time)
			return `${(temp.getMonth()+1)}월 ${temp.getDate()}일 ${temp.getHours()}시`
		},
		matchFilter(stadium,time){
			const utc = (x => (parseInt(x/3600/1000/24)*24 +
				(new Date(x).getHours() >= 9 ? 15 : 39))*3600*1000)
			return (stadium === '' ? this.table : this.table.filter(i=> i.stadium == stadium))
				.filter(i => time <= i.time && i.time < utc(time))
				.sort((a,b) => a.time > b.time ? 1 : (a.time < b.time ? -1 : 0))
		},
		click(){
			this.$router.push({path:`/futsal/match`}) 
		}
	}
})
</script>
<style scoped>
td:not(td){
	vertical-align: middle;
}
</style>