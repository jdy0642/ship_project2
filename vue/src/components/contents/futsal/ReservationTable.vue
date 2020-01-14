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
		<v-data-table style="vertical-align: interit"
		:headers="headers" :items="matchFilter(propstadium,proptime)"
		class="elevation-1" hide-default-footer>
			<template v-slot:item.time="{item}">
				{{timeToDate(item.time)}}
				<!-- <v-btn @click="click(item)">{{timeToDate(item.time)}}</v-btn> -->
			</template>
			<template v-slot:item.gender="{item}">
				<v-img height="30" width="30" 
				:src="require(`@/assets/img/matchRule/${item.gender}.svg`)"/>
			</template>
			<template v-slot:item.difficulty="{item}">
				<v-row width="1">
					<v-img width="1"
						v-for="n of parseInt(item.difficulty)" 
						:key="n" src="@/assets/img/matchRule/star.svg">
					</v-img>
				</v-row>
			</template>
			<template v-slot:item.remain="{item}">
				<v-btn @click="selectMatch(item)" :color="item.remain > 3 ? '#2478FF' : '#FF3636'">
					{{item.remain > 3 ? `${item.remain}자리 남음` : '마감임박'}}
				</v-btn>
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
				{ text: '경기장', value: 'stadiumName' },
				{ text: '인원', value: 'num' },
				{ text: '성별', value: 'gender'},
				{ text: '난이도', value: 'difficulty' },
				{ text: '예약가능 인원', align: 'center',sortable: false,value: 'remain'}
			],
			table: [],
		}
	},
	created(){
		const rannum = () => ['4vs4','5vs5','6vs6'][parseInt(Math.random()*3)]
		const rangender = () => ['fm','ma'][parseInt(Math.random()*2)]
		const ranrating = () => parseInt(Math.random()*3+1)	
		const ranrule = () => `${rannum()},${rangender()},${ranrating()},4,5`
		const ranloc = () => ['신촌','강남','용인','종각','평양','부산','응암','보스턴'][parseInt(Math.random()*8)]
		const rantime = x => x + Math.random()*1000*3600*24*13
		const addr = () => ['어디어디어디 주소','거기어디어디 주소','어디어디거기 주소','어디어디 주소'][parseInt(Math.random()*4)]
		const ranfacility = () => 'size0,shower0,park0,shoes0,wear0'
		const remain = () => parseInt(Math.random()*5+1)
		let table = Array.from({length : 200},(_,i) => ({
			id: i, time: rantime(this.proptime), stadiumName: ranloc(),
			addr: addr(), rule: ranrule(), facility: ranfacility(), 
			stadiumImg: '1,2,3', adminName: '펭수', remain: remain() 
			}))
		//위쪽은 랜덤값. 실제는 axios로 받아와야함.
		table.map(x =>{x.num = x.rule.split(",")[0]
					x.gender = x.rule.split(",")[1]
					x.difficulty = x.rule.split(",")[2]
					x.shoes = x.rule.split(",")[3]
					x.minmax = x.rule.split(",")[4]
					x.groundSize = x.facility.split(",")[0]
					x.shower = x.facility.split(",")[1]
					x.parking = x.facility.split(",")[2]
					x.shoesRental = x.facility.split(",")[3]
					x.dressRental = x.facility.split(",")[4]
		})
		this.table = table
		store.state.matchList = table
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
		selectMatch(param){
			store.state.selectMatch = param
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