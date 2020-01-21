<template>
<div class="ma-auto">
	<!-- 부트 스트랩 
		<div v-for="(match,index) of matchFilter(propstadium,proptime)" :key="index"
	style="display: grid; grid-template-columns: 10% 20% 45% 25%; border: solid 1px;">
		<div v-for="(item,key) of match" :key="key" @click="click(item,key)" style="text-align: center">
			{{key!='time' ? item : timeToDate(item)}}
		</div>
	</div> -->
	<h3>{{timeToDate(propTime)}}  {{propStadium}}</h3>
	<v-card raised class="ma-auto">
		<v-data-table
		:headers="headers" :items="table"
		class="elevation-1 ma-2" hide-default-footer>
			<template v-slot:item.time="{item}">
				{{timeToDate(item.time)}}
				<!-- <v-btn @click="click(item)">{{timeToDate(item.time)}}</v-btn> -->
			</template>
			<template v-slot:item.num="{item}">
				{{`${item.num}vs${item.num}`}} 
			</template>
			<template v-slot:item.gender="{item}">
				<v-img height="30" width="30" 
				:src="require(`@/assets/img/matchRule/${item.gender}.svg`)"/>
			</template>
			<template v-slot:item.difficulty="{item}">
				<v-img class="d-inline-flex"
					v-for="n of parseInt(item.difficulty)" 
					:key="n" src="@/assets/img/matchRule/star.svg">
				</v-img>
			</template>
			<template v-slot:item.remain="{item}">
				<v-btn @click="selectMatch(item)" :color="item.remain > 3 ? '#2478FF' : 
					item.remain!=0 ? '#FF3636' : '#8C8C8C'">
					{{item.remain > 3 ? `${item.remain}자리 남음` : item.remain!=0 ? '마감임박' : '예약마감'}}
				</v-btn>
			</template>
		</v-data-table>
	</v-card>
</div>
</template>
<script>
import {store} from '@/store'
export default{
	props: [
		'propTime',
		'propStadium',
		'propTable'
	],
	data(){
		return{
			headers: [
				{ text: '경기날짜', width: 200, value: 'time' },
				{ text: '경기장', value: 'stadiumname' },
				{ text: '인원', value: 'num' },
				{ text: '성별', value: 'gender'},
				{ text: '난이도', value: 'difficulty' },
				{ text: '예약가능 인원', align: 'center',sortable: false,value: 'remain'}
			],
		}
	},
	computed: {
		table(){
			return this.propTable
		}
	},
	methods:{
		timeToDate(time){
			const temp = new Date(time)
			return `${(temp.getMonth()+1)}월 ${temp.getDate()}일 ${temp.getHours()}시`
		},
		selectMatch(param){
			if(param.remain != 0){
				store.state.selectMatch = param
				this.$router.push({ name: 'futsalmatch', params: { matchId: param.futsalmatchseq }})
			}
		}
	}
}
</script>
<style scoped>
td:not(td){
	vertical-align: middle;
}
</style>